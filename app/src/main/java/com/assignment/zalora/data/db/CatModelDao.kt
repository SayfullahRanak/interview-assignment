package com.assignment.zalora.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.zalora.data.entities.CatModel

@Dao
interface CatModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doggoModel: List<CatModel>)

    @Query("SELECT * FROM CatModel")
    fun getAllCatModel(): PagingSource<Int, CatModel>

    @Query("DELETE FROM CatModel")
    suspend fun clearAllCats()



}