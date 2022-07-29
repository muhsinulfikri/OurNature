package com.example.ournature.ui.news

import com.example.ournature.data.api.datasource.NewsListDataSource
import com.example.ournature.data.api.response.NewsListResponse
import com.example.ournature.data.local.room.datasource.HistoryNewsDataSource
import com.example.ournature.data.local.room.entity.HistoryNews
import javax.inject.Inject

class NewsRepository
@Inject constructor(
    private val newsListDataSource: NewsListDataSource,
    private val historyNewsDataSource: HistoryNewsDataSource
) {

    suspend fun getNewsList(): NewsListResponse {
        return newsListDataSource.getNewsList()
    }

    suspend fun insertHistoryNews(historyNews: HistoryNews): Int {
        return historyNewsDataSource.insertHistoryNews(historyNews)
    }
}