package com.sevenpeakssoftware.sayfullah.ui.carlist.viewmodel

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.zalora.data.repo.MainRepo
import com.sayfullah.utils.base.BaseViewModel
import com.sayfullah.utils.tools.NetworkUtil.Companion.TYPE_NOT_CONNECTED
import com.sayfullah.utils.tools.NetworkUtil.Companion.getConnectivityStatus
import com.sevenpeakssoftware.sayfullah.data.*
import com.sevenpeakssoftware.sayfullah.db.AppDatabase
import com.sevenpeakssoftware.sayfullah.paging.CarsRepository
import com.sevenpeakssoftware.sayfullah.utils.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class CarRemoteViewModel @ViewModelInject constructor(private val context: Context, private val mainRepo: MainRepo, private val appDatabase: AppDatabase) : BaseViewModel() {


    val carListlv = MutableLiveData<Response>()
    val networkStatus = MutableLiveData<Boolean>()

//    @ExperimentalPagingApi
//    fun fetchCatImages() : Flow<PagingData<Content>>{
//        return CarsRepository.getInstance(
//            mainRepo,
//            appDatabase
//        ).letCatsImagesFlowDb().map { it }.cachedIn(viewModelScope)
//    }

    suspend fun fetchCarList(){
        var netWorkStatus = true
        withContext(Dispatchers.IO) {
            if (appDatabase.getCarModelDao().getAllCarModel().isEmpty()) carListlv.postValue(Loading("Loading car List..."))
            else carListlv.postValue(Success(appDatabase.getCarModelDao().getAllCarModel()))
        }

        try {
            val apiResponse = mainRepo.getCars()
            if(apiResponse.code() == 200){
                appDatabase.getCarModelDao().clearAllCars()
                carListlv.postValue(Success(apiResponse.body()?.content))
                appDatabase.getCarModelDao().insertAll(apiResponse.body()?.content!!)
            }else{
                carListlv.postValue(ErrorIn(0, apiResponse.message()))
            }
        }catch (exception: IOException){
            carListlv.postValue(ErrorIn(1, exception.message))
//            carListlv.postValue(Success(appDatabase.getCarModelDao().getAllCarModel()))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }catch (exception: HttpException){
            carListlv.postValue(ErrorIn(2, exception.message))
//            carListlv.postValue(Success(appDatabase.getCarModelDao().getAllCarModel()))
            netWorkStatus = getConnectivityStatus(MyApplication.appInstance) != TYPE_NOT_CONNECTED
        }
        networkStatus.postValue(netWorkStatus)
//
    }




    fun retry() {
//        sourceFactory.catsDataSourceLiveData.value!!.retry()
    }

}