package com.sevenpeakssoftware.sayfullah.rest

import com.sevenpeakssoftware.sayfullah.data.CarBaseResponse
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single


interface RestService {

    @GET("/article/get_articles_list")
    suspend fun getCars(): CarBaseResponse
}