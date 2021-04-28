package com.assignment.zalora.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.zalora.data.entities.CatModel
import com.assignment.zalora.data.repo.MainRepo
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CatImagePagingSource(private val mainRepo: MainRepo) : PagingSource<Int, CatModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {

            val response  = mainRepo.getCats(params.loadSize,page)

            LoadResult.Page(
                response, prevKey = if( page == 1) null else page - 1,
                nextKey = if(response.isEmpty()) null else page + 1
            )

        }catch (exception : IOException){
            return LoadResult.Error(exception)
        }catch (exception : HttpException){
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CatModel>): Int? {
        TODO("Not yet implemented")
    }


}