package com.example.ournature.data.local.room.dao

import androidx.room.*
import com.example.ournature.data.local.room.entity.HistoryNews

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryNews(historyNews: HistoryNews): Int = 0

    @Delete
    suspend fun deleteHistoryNews(historyNews: HistoryNews): Int

    @Query("SELECT * FROM historyNews")
    suspend fun getAllHistoryNews(): List<HistoryNews>
}