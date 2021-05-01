package com.sevenpeakssoftware.sayfullah.rest

import com.sevenpeakssoftware.sayfullah.data.CarBaseResponse
import retrofit2.Response
import retrofit2.http.GET



interface RestService {

    @GET("application/119267/article/get_articles_list")
    suspend fun getCars(): Response<CarBaseResponse>
}