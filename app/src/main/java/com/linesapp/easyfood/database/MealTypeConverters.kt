package com.linesapp.easyfood.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class MealTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromAny(attribute: Any?): String{
        return gson.toJson(attribute)
    }
    @TypeConverter
    fun toAny(attribute: String?): Any?{
        return gson.fromJson(attribute, Any::class.java)
    }
}