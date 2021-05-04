package com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.core.internal.deps.guava.collect.Iterators.elementsEqual
import com.sevenpeakssoftware.sayfullah.data.Content
import com.sevenpeakssoftware.sayfullah.data.ErrorIn
import com.sevenpeakssoftware.sayfullah.data.Success
import com.sevenpeakssoftware.sayfullah.db.AppDatabase
import com.sevenpeakssoftware.sayfullah.repo.MainRepo
import com.sevenpeakssoftware.sayfullah.testutils.getOrAwaitValue
import com.sevenpeakssoftware.sayfullah.utils.MyApplication
import com.sevenpeakssoftware.sayfullah.testutils.RestServiceTest
import com.sevenpeakssoftware.sayfullah.testutils.TestCons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarRemoteViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var restServiceTest: RestServiceTest

    lateinit var carRemoteViewModel: CarRemoteViewModel

    lateinit var database: AppDatabase

    @Before
    fun setUp() {

        restServiceTest = RestServiceTest()
           database = Room.inMemoryDatabaseBuilder(MyApplication.appInstance, AppDatabase::class.java).allowMainThreadQueries().build()

        val mainRepo = MainRepo(restServiceTest)

        carRemoteViewModel = CarRemoteViewModel(mainRepo,database)

    }


    @Test
    fun fetchCarList_populatinglist_when_api_response_success()= runBlocking<Unit> {

        RestServiceTest.setCarList(TestCons.carList)

        carRemoteViewModel.fetchCarList()

        carRemoteViewModel.carListlv.getOrAwaitValue().let {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    assertTrue(!carListResponse.isEmpty())
                }
            }
        }

    }

    @Test
    fun fetchCarList_empty_when_api_response_empty()= runBlocking<Unit> {

        RestServiceTest.setCarList(TestCons.emptyList)

        carRemoteViewModel.fetchCarList()

        carRemoteViewModel.carListlv.getOrAwaitValue().let {
            when(it){
                is Success ->{
                    val carListResponse = it.data as List<*>
                    assertTrue(carListResponse.isEmpty())
                }
            }
        }

    }

    @Test
    fun fetchCarList_Error_when_response_code_not_200()= runBlocking<Unit> {

        RestServiceTest.setCarList(TestCons.carList)
        RestServiceTest.setResponseCode(500)

        carRemoteViewModel.fetchCarList()

        carRemoteViewModel.carListlv.getOrAwaitValue().let {
            when(it){
                is ErrorIn ->{
                    assertTrue(true)
                }
                is Success ->{
                    assertTrue(false)
                }
            }
        }

    }


    // test offline support

    @Test
    fun fetchCarList_database_contains_old_list_when_no_internet()= runBlocking<Unit> {

        var carListResponse : List<Content> = ArrayList<Content>()

        RestServiceTest.setCarList(TestCons.carList)

        carRemoteViewModel.fetchCarList()

        carRemoteViewModel.carListlv.getOrAwaitValue().let {
            when(it){
                is Success ->{
                    carListResponse = it.data as List<Content>
                }
            }
        }

        RestServiceTest.setResponseCode(500)

        carRemoteViewModel.fetchCarList()

        val localStoredCarList : List<Content> = database.getCarModelDao().getAllCarModel()

        assertTrue(carListResponse.equals(localStoredCarList))
    }

    @Test
    fun fetchCarList_networks_when_no_network()= runBlocking<Unit> {

        RestServiceTest.setCarList(TestCons.carList)
        RestServiceTest.setMockBaseUrl("http://www.abc.com")

        carRemoteViewModel.fetchCarList()

        carRemoteViewModel.networkStatus.getOrAwaitValue().let {
            assertTrue(it)
        }

    }

}