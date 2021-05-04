package com.sevenpeakssoftware.sayfullah.repo

import com.sevenpeakssoftware.sayfullah.data.CarBaseResponse
import com.sevenpeakssoftware.sayfullah.rest.RestService
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class MainRepo @Inject
constructor(private val repoService: RestService) {


    suspend fun getCars(): Response<CarBaseResponse> {

        return repoService.getCars()
    }
}