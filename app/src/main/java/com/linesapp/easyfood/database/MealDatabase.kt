package com.linesapp.easyfood.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.linesapp.easyfood.model.random_meal.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
@TypeConverters(MealTypeConverters::class)

abstract class MealDatabase: RoomDatabase() {
    abstract fun dao(): MealDao

    companion object{
        @Volatile
        var INSTANCE: MealDatabase? = null

        fun getInstance(context: Context): MealDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    "meal_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }


}