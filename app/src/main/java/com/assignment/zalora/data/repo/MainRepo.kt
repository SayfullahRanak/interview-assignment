package com.assignment.zalora.data.repo

import com.assignment.zalora.data.entities.cat
import com.assignment.zalora.data.rest.RestService
import io.reactivex.Single
import javax.inject.Inject

class MainRepo @Inject
constructor(private val repoService: RestService) {

    fun getUsers(pageLimit: Long, perNumber: Long): Single<List<cat>> {

        return repoService.getUsers( pageLimit, perNumber,"png","Desc")
    }
}