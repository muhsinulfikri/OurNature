package com.example.ournature.data.local.room.datasource

import com.example.ournature.data.local.room.dao.HistoryDao
import com.example.ournature.data.local.room.entity.HistoryNews
import javax.inject.Inject

class HistoryNewsDataSourceImpl @Inject constructor(private val dao: HistoryDao): HistoryNewsDataSource {
    override suspend fun insertHistoryNews(historyNews: HistoryNews): Int {
        return dao.insertHistoryNews(historyNews)
    }

    override suspend fun deleteHistoryNews(historyNews: HistoryNews): Int {
        return dao.deleteHistoryNews(historyNews)
    }

    override suspend fun getAllHistoryNews(): List<HistoryNews> {
        return dao.getAllHistoryNews()
    }
}