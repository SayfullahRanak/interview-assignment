package com.assignment.zalora.data.offlinesupport

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.assignment.zalora.data.db.AppDatabase
import com.assignment.zalora.data.db.RemoteKeys
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo
import com.assignment.zalora.data.datasource.CatImagesRepository.Companion.DEFAULT_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class CatMediator(private val mainRepo: MainRepo, private val appDatabase: AppDatabase)
    : RemoteMediator<Int, CatModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CatModel>
    ): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)
        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }
            else -> {
                val keyData = pageKeyData as Int
                keyData

            }
        }

        try {
            val response = mainRepo.getCats(page, state.config.pageSize)
            val isEndOfList = response.isEmpty()
            appDatabase.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRepoDao().clearRemoteKeys()
                    appDatabase.getCatModelDao().clearAllCats()
                }
                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                appDatabase.getRepoDao().insertAll(keys)
                appDatabase.getCatModelDao().insertAll(response)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }


    /**
     * this returns the page key or the final end of list success result
     */
    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, CatModel>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, CatModel>): RemoteKeys? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { cat -> appDatabase.getRepoDao().remoteKeysDoggoId(cat.id) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, CatModel>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { cat -> appDatabase.getRepoDao().remoteKeysDoggoId(cat.id) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, CatModel>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                appDatabase.getRepoDao().remoteKeysDoggoId(repoId)
            }
        }
    }


}