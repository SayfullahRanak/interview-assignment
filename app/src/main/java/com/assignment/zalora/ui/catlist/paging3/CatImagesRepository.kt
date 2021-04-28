package com.assignment.zalora.ui.catlist.paging3

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.zalora.data.db.AppDatabase
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.offlinesupport.CatMediator
import com.assignment.zalora.data.repo.MainRepo
import kotlinx.coroutines.flow.Flow


/**
 * repository class to manage the data flow and map it if needed
 */

@ExperimentalPagingApi
class CatImagesRepository(private val mainRepo: MainRepo,private val appDatabase: AppDatabase) {


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get cat data repository instance
        fun getInstance(mainRepo: MainRepo, appDatabase: AppDatabase) = CatImagesRepository(mainRepo, appDatabase)
    }

    /**
     * calling the paging source to give results from api calls
     * and returning the results in the form of flow [Flow<PagingData<Cat>>]
     * since the [PagingDataAdapter] accepts the [PagingData] as the source in later stage
     */

    fun letCatsImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()) : Flow<PagingData<CatModel>>{
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {CatImagePagingSource(mainRepo)}
        ).flow
    }


    fun letCatsImagesFlowDb(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<CatModel>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { appDatabase.getCatModelDao().getAllCatModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = CatMediator(mainRepo, appDatabase)
        ).flow
    }


    fun getDefaultPageConfig() :PagingConfig{
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE,enablePlaceholders = false)
    }
}