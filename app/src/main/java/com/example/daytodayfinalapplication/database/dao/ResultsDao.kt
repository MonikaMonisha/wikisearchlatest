package com.example.daytodayfinalapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.daytodayfinalapplication.database.model.CachedResult
import io.reactivex.Single

@Dao
abstract class ResultsDao {

    @Query("SELECT * FROM results where pageId IN (:pageIds)")
    abstract fun getResults(pageIds: List<Int>): Single<List<CachedResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(results: List<CachedResult>)

    @Query("DELETE from results")
    abstract fun deleteResults()

}
