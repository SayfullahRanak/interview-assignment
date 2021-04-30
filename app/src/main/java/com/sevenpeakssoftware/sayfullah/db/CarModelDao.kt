package com.sevenpeakssoftware.sayfullah.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenpeakssoftware.sayfullah.data.Content

@Dao
interface CarModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doggoModel: List<Content>)

    @Query("SELECT * FROM Content")
    fun getAllCatModel(): PagingSource<Int, Content>

    @Query("DELETE FROM Content")
    suspend fun clearAllCats()



}