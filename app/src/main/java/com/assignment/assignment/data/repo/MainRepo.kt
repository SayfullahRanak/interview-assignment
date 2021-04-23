package com.assignment.assignment.data.repo

import com.assignment.assignment.data.rest.RestService
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {

//    fun getRestaurantList(): Observable<Response<RestaurantList>> {
//
//        return repoService.RestaurantList()
//    }
}