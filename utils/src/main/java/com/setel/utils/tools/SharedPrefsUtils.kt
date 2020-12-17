package com.setel.utils.tools

import com.orhanobut.hawk.Hawk

class SharedPrefsUtils {

    companion object {

        fun <T> saveData(key: String, value: T) = Hawk.put(key, value)

        fun <T> getData(key: String, defaultValue: T?): T? =
            if (defaultValue != null) Hawk.get(key, defaultValue) else Hawk.get(key)

        fun removeData(key: String) = Hawk.delete(key)


    }
}