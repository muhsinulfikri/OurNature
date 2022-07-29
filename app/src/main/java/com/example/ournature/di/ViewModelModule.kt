package com.example.ournature.di

import com.example.ournature.base.GenericViewModelFactory
import com.example.ournature.ui.history.HistoryRepository
import com.example.ournature.ui.history.HistoryViewModel
import com.example.ournature.ui.news.NewsRepository
import com.example.ournature.ui.news.NewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    @Provides
    @ActivityScoped
    fun provideNewsViewModel(newsRepository: NewsRepository): NewsViewModel {
        return GenericViewModelFactory(NewsViewModel(newsRepository)).create(
            NewsViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideHistoryViewModel(historyRepository: HistoryRepository): HistoryViewModel{
        return GenericViewModelFactory(HistoryViewModel(historyRepository)).create(
            HistoryViewModel::class.java
        )
    }

}