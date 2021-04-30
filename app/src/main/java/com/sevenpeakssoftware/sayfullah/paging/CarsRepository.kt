package com.sevenpeakssoftware.sayfullah.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.zalora.data.repo.MainRepo
import com.sevenpeakssoftware.sayfullah.data.Content
import com.sevenpeakssoftware.sayfullah.db.AppDatabase
import kotlinx.coroutines.flow.Flow


/**
 * repository class to manage the data flow and map it if needed
 */

@ExperimentalPagingApi
class CarsRepository(private val mainRepo: MainRepo, private val appDatabase: AppDatabase) {


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 4

        //get cat data repository instance
        fun getInstance(mainRepo: MainRepo, appDatabase: AppDatabase) =
            CarsRepository(
                mainRepo,
                appDatabase
            )
    }


    /**
     * calling the paging source to give results from database
     * and returning the results in the form of flow [Flow<PagingData<Cat>>]
     * since the [PagingDataAdapter] accepts the [PagingData] as the source in later stage
     */
    fun letCatsImagesFlowDb(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Content>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initialized")

        val pagingSourceFactory = { appDatabase.getCarModelDao().getAllCatModel() }
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = CarMediator(mainRepo, appDatabase)
        ).flow
    }


    fun getDefaultPageConfig() :PagingConfig{
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE,enablePlaceholders = false)
    }
}