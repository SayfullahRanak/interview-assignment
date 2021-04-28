package com.assignment.zalora.data.repo

import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.rest.RestService
import io.reactivex.Single
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {


    suspend fun getCats(pageLimit: Int, pageNumber: Int): List<CatModel> {

        return repoService.getCats( pageLimit, pageNumber,"png","Desc")
    }
}