package com.example.ournature.di

import android.content.Context
import androidx.room.Room
import com.example.ournature.data.local.room.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, HistoryDatabase::class.java, "bookmark_news_db").build()

    @Provides
    @Singleton
    fun providesDao(database: HistoryDatabase) = database.historyNewsDao()
}