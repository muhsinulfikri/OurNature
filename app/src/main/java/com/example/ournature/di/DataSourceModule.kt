package com.example.ournature.di

import com.example.ournature.data.api.datasource.NewsListDataSource
import com.example.ournature.data.api.services.ApiServices
import com.example.ournature.data.local.room.dao.HistoryDao
import com.example.ournature.data.local.room.datasource.HistoryNewsDataSource
import com.example.ournature.data.local.room.datasource.HistoryNewsDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsDataSource(newsApiServices: ApiServices): NewsListDataSource{
        return NewsListDataSource(newsApiServices)
    }

    @Singleton
    @Provides
    fun provideHistoryNewsDataSource(historyNewsDao: HistoryDao): HistoryNewsDataSource{
        return HistoryNewsDataSourceImpl(historyNewsDao)
    }

}