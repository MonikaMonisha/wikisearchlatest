package com.example.daytodayfinalapplication.database.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class CachedResult(
    @PrimaryKey
    var pageId: Int,
    var title: String,
    var description: String?,
    var imageUrl: String?
)
