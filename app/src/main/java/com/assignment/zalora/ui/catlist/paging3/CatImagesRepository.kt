package com.assignment.zalora.ui.catlist.paging3

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo
import kotlinx.coroutines.flow.Flow


/**
 * repository class to manage the data flow and map it if needed
 */

//@ExperimentalPagingApi
class CatImagesRepository(private val mainRepo: MainRepo) {


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20

        //get cat data repository instance
        fun getInstance(mainRepo: MainRepo) = CatImagesRepository(mainRepo)
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


    fun getDefaultPageConfig() :PagingConfig{
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE,enablePlaceholders = false)
    }
}