package com.assignment.zalora.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.zalora.data.entities.CatModel

@Database(version = 1, entities = [CatModel::class,RemoteKeys::class], exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getCatModelDao() : CatModelDao

    companion object{
        val CAT_DB = "cat.db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase = INSTANCE ?: synchronized(this){
            INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, CAT_DB)
                .build()
        }
}