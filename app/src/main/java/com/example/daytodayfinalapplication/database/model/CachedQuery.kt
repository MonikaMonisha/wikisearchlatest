package com.example.daytodayfinalapplication.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries")
data class CachedQuery(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,

    @ColumnInfo(index = true)
    var text: String,

    var lastCachedTime: Long,

    var pageIds: String
)
