package com.assignment.zalora.data.rest

import com.assignment.zalora.data.entities.Cat
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single


interface RestService {


//    @GET("/homework")
//    fun RestaurantList():
//            Observable<Response<RestaurantList>>

//    @GET(Urls.FetchNewsList)
//    fun fetchListNews(
//        @Query("source") source: String?,
//        @Query("apiKey") apiKey: String?
//    ): Observable<JsonElement?>?

    @GET("v1/images/search?")
    fun getUsers(@Query("limit") perPageLimit: Long, @Query("page") perPage: Long,
                 @Query("mime_types") mimneType: String, @Query("order") orderType: String): Single<List<Cat>>

}