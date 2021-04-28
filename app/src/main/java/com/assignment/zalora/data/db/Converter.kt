package com.assignment.zalora.data.db

import androidx.room.TypeConverter
import com.assignment.zalora.data.entities.Breed
import com.google.gson.Gson

class Converter {

    @TypeConverter
    fun fromObjectList(breedList : List<Breed> ) : String{
        return Gson().toJson(breedList)
    }

    @TypeConverter
    fun toObjectList(list: String ) : List<Breed>{
        return Gson().fromJson(list,Array<Breed>::class.java).asList()
    }
}