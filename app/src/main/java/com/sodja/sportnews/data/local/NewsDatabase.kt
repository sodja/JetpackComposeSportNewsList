package com.sodja.sportnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sodja.sportnews.data.local.dao.NewsDao
import com.sodja.sportnews.data.local.entities.NewEntity

@Database(entities = [NewEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}