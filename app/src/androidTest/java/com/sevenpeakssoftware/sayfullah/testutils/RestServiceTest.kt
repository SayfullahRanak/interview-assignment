package com.sevenpeakssoftware.sayfullah.testutils

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

        const val mockBaseUrl = "http://www.mocky.io/v2/"

        private var mockCarList = ""

        private var responseCode : Int = responseCodeSuccess

        private var baseUrl : String = mockBaseUrl

        fun setCarList(list: String){
            mockCarList = list
        }

        fun setResponseCode(code :Int){
            responseCode = code
        }

        fun setMockBaseUrl(url :String){
            baseUrl = url
        }

        fun create() : MockRequest {

            return Retrofit.Builder()
                .client(OkHttpClient.Builder().addInterceptor(MockInterceptor(mockCarList,
                        responseCode)).build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
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
        return create().getMokCars()
    }



}