package com.sevenpeakssoftware.sayfullah.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sevenpeakssoftware.sayfullah.data.Content

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

@Database(version = 1, entities = [Content::class], exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCarModelDao() : CarModelDao

    companion object{
        private const val CAR_DB = "car.db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, CAR_DB)
                .build()
        }
}