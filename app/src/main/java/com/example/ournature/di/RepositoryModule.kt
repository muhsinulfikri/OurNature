package com.example.ournature.di

import com.example.ournature.data.api.datasource.NewsListDataSource
import com.example.ournature.data.local.room.datasource.HistoryNewsDataSource
import com.example.ournature.ui.history.HistoryRepository
import com.example.ournature.ui.news.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(newsListDataSource: NewsListDataSource, historyNews: HistoryNewsDataSource): NewsRepository {
        return NewsRepository(newsListDataSource, historyNews)
    }

    @Singleton
    @Provides
    fun provideHistoryRepository(historyNewsDataSource: HistoryNewsDataSource): HistoryRepository{
        return HistoryRepository(historyNewsDataSource)
    }

}