package com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sevenpeakssoftware.sayfullah.data.ErrorIn
import com.sevenpeakssoftware.sayfullah.data.Loading
import com.sevenpeakssoftware.sayfullah.data.Response
import com.sevenpeakssoftware.sayfullah.data.Success
import com.sevenpeakssoftware.sayfullah.db.AppDatabase
import com.sevenpeakssoftware.sayfullah.repo.MainRepo
import com.sevenpeakssoftware.sayfullah.testutils.CoroutineTestRule
import com.sevenpeakssoftware.sayfullah.testutils.getOrAwaitValue
import com.sevenpeakssoftware.sayfullah.utils.MyApplication
import com.sevenpeakssoftware.sayfullah.utils.RestServiceTest
import com.sevenpeakssoftware.sayfullah.utils.TestCons
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Error

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CarRemoteViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    lateinit var restServiceTest: RestServiceTest

    lateinit var carRemoteViewModel: CarRemoteViewModel

    @Before
    fun setUp() {

        restServiceTest = RestServiceTest()
           val database = Room.inMemoryDatabaseBuilder(MyApplication.appInstance, AppDatabase::class.java).allowMainThreadQueries().build()

        val mainRepo = MainRepo(restServiceTest)

        carRemoteViewModel = CarRemoteViewModel(mainRepo,database)

    }

//    @Test
//    fun getCarListlv()= runBlocking<Unit> {
//
//        RestServiceTest.setCarList(TestCons.carList)
//
//        carRemoteViewModel.fetchCarList()
//
//        val result : Unit = carRemoteViewModel.carListlv.getOrAwaitValue().let {
//            when(it){
//                is Success ->{
//                    val carListResponse = it.data as List<*>
//                    assertTrue(!carListResponse.isEmpty())
//                }
//            }
//        }
//
//    }

    @Test
    fun fetchCarList_populatinglist_when_api_response_success()= runBlocking<Unit> {

        RestServiceTest.setCarList(TestCons.carList)

        carRemoteViewModel.fetchCarList()

        val result : Unit = carRemoteViewModel.carListlv.getOrAwaitValue().let {
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

        val result : Unit = carRemoteViewModel.carListlv.getOrAwaitValue().let {
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


    @Test
    fun getNetworkStatus() {
    }

    @Test
    fun fetchCarList() {
    }
}