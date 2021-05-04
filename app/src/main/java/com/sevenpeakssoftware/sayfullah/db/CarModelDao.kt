package com.sevenpeakssoftware.sayfullah.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sevenpeakssoftware.sayfullah.data.Content

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@Dao
interface CarModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(carModel: List<Content>)

    @Query("SELECT * FROM Content")
    suspend fun getAllCarModel(): List<Content>

    @Query("DELETE FROM Content")
    suspend fun clearAllCars()



}