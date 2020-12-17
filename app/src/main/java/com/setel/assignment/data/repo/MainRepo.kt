package com.setel.assignment.data.repo

import com.setel.assignment.data.rest.RestService
import com.setel.assignment.data.entities.RestaurantList
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {

    fun getRestaurantList(): Observable<Response<RestaurantList>> {

        return repoService.RestaurantList()
    }
}