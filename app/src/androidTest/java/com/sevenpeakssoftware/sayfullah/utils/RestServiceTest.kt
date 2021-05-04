package com.sevenpeakssoftware.sayfullah.utils

import com.sevenpeakssoftware.sayfullah.data.CarBaseResponse
import com.sevenpeakssoftware.sayfullah.rest.RestService
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


/**
 * Created by Md Sayfullah Al Noman Ranak on 04/05/2021.
 */

class RestServiceTest : RestService {

    companion object NetworkClient {

        const val responseCodeSuccess = 200

        private var mockCarList = ""

        private var responseCode : Int = responseCodeSuccess

        fun setCarList(list: String){
            mockCarList = list
        }

        fun setResponseCode(code :Int){
            responseCode = code
        }

        fun create() : MockRequest {

            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(MockInterceptor(mockCarList,
                    responseCode)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://www.mocky.io/v2/")
                .build().create(MockRequest::class.java)

        }

        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create()
        }

    }


    interface MockRequest{

        @GET("login")
        suspend fun getMokCars() : Response<CarBaseResponse>
    }


    override suspend fun getCars(): Response<CarBaseResponse> {
        return NetworkClient.create().getMokCars()
    }



}