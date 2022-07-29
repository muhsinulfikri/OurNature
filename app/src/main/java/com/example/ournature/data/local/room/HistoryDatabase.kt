package com.example.ournature.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ournature.data.local.room.dao.HistoryDao
import com.example.ournature.data.local.room.entity.HistoryNews

@Database(entities = [HistoryNews::class], version = 1, exportSchema = true)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyNewsDao(): HistoryDao
}