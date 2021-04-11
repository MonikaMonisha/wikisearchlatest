package com.example.daytodayfinalapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.daytodayfinalapplication.database.model.CachedQuery
import io.reactivex.Single

@Dao
abstract class QueriesDao {

    @Query("SELECT * FROM queries WHERE text = :query")
    abstract fun getQuery(query: String): Single<List<CachedQuery>>

    @Query("SELECT COUNT(id) FROM queries WHERE text = :query")
    abstract fun isQueryCached(query: String): Single<Int>

    @Insert
    abstract fun insertQuery(query: CachedQuery)

}
