package com.example.ournature.data.local.room.datasource

import com.example.ournature.data.local.room.entity.HistoryNews

interface HistoryNewsDataSource {
    suspend fun insertHistoryNews(historyNews: HistoryNews): Int

    suspend fun deleteHistoryNews(historyNews: HistoryNews): Int

    suspend fun getAllHistoryNews(): List<HistoryNews>
}