package com.sevenpeakssoftware.sayfullah.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sevenpeakssoftware.sayfullah.data.ContentX

/**
 * Created by Md Sayfullah Al Noman Ranak
 */

class Converter {

    @TypeConverter
    fun fromObjectList(breedList : List<ContentX> ) : String{
        return Gson().toJson(breedList)
    }

    @TypeConverter
    fun toObjectList(list: String ) : List<ContentX>{
        return Gson().fromJson(list,Array<ContentX>::class.java).asList()
    }

    @TypeConverter
    fun fromStringList(breedList : List<String> ) : String{
        return Gson().toJson(breedList)
    }

    @TypeConverter
    fun toStringList(list: String ) : List<String>{
        return Gson().fromJson(list,Array<String>::class.java).asList()
    }
}