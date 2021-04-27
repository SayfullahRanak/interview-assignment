package com.assignment.zalora.data.offlinesupport

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.assignment.zalora.data.db.AppDatabase
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo

@ExperimentalPagingApi
class CatMediator(private val mainRepo: MainRepo, private val appDatabase: AppDatabase)
    : RemoteMediator<Int, CatModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatModel>
    ): MediatorResult {


    }
}