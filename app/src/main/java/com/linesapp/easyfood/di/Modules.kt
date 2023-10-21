package com.linesapp.easyfood.di

import android.content.Context
import com.linesapp.easyfood.api.MealApi
import com.linesapp.easyfood.api.RetrofitInstance
import com.linesapp.easyfood.database.MealDao
import com.linesapp.easyfood.database.MealDatabase
import com.linesapp.easyfood.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideApi(): MealApi{
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun provideRepository(mealDB: MealDatabase): MealRepository{
        return MealRepository(mealDB)
    }

    @Provides
    @Singleton
    fun provideMealDatabase(@ApplicationContext context: Context): MealDatabase{
        return MealDatabase.getInstance(context)
    }
}