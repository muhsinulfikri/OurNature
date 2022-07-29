package com.example.ournature.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "historyNews")
data class HistoryNews(
    @PrimaryKey
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "title")
    var title: String? = null
//    @ColumnInfo(name = "image")
//    var image: String? = null,
//    @ColumnInfo(name = "url")
//    var url: String? = null
) : Parcelable