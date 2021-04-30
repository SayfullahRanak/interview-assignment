package com.sevenpeakssoftware.sayfullah.rest

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single


interface RestService {

    @GET("v1/images/search?")
    suspend fun getCats(@Query("limit") perPageLimit: Int, @Query("page") perPage: Int,
                 @Query("mime_types") mimneType: String, @Query("order") orderType: String): List<Any>

}