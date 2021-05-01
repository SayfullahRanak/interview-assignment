package com.assignment.zalora.data.repo

import com.sevenpeakssoftware.sayfullah.data.CarBaseResponse
import com.sevenpeakssoftware.sayfullah.rest.RestService
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {


    suspend fun getCars(pageLimit: Int, pageNumber: Int): Response<CarBaseResponse> {

        return repoService.getCars( )
    }

    suspend fun getCars(): Response<CarBaseResponse> {

        return repoService.getCars( )
    }
}