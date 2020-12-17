package com.setel.assignment.data.rest

import com.setel.assignment.data.entities.*
import com.setel.assignment.data.entities.RestaurantList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RestService {


    @GET("/homework")
    fun RestaurantList():
            Observable<Response<RestaurantList>>

}