package com.example.ournature.ui.history

import com.example.ournature.data.local.room.datasource.HistoryNewsDataSource
import com.example.ournature.data.local.room.entity.HistoryNews
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val historyNewsDataSource: HistoryNewsDataSource) {
    suspend fun getAllHistoryNews(): List<HistoryNews> {
        return historyNewsDataSource.getAllHistoryNews()
    }
}