package com.example.daytodayfinalapplication.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.daytodayfinalapplication.database.dao.QueriesDao
import com.example.daytodayfinalapplication.database.dao.ResultsDao
import com.example.daytodayfinalapplication.database.model.CachedQuery
import com.example.daytodayfinalapplication.database.model.CachedResult
import javax.inject.Inject

@Database(entities = [CachedQuery::class, CachedResult::class], version = 1, exportSchema = true)
abstract class SearchDatabase: RoomDatabase() {

    abstract fun queriesDao(): QueriesDao

    abstract fun resultsDao(): ResultsDao

    companion object {

        private var INSTANCE: SearchDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): SearchDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            SearchDatabase::class.java, "search.db"
                        ).build()
                    }
                    return INSTANCE as SearchDatabase
                }
            }
            return INSTANCE as SearchDatabase
        }
    }

}
