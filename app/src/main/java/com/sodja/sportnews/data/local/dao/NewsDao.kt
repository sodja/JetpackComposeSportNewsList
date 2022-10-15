package com.sodja.sportnews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sodja.sportnews.data.local.entities.NewEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM NEW_ENTITY")
    fun getAll(): List<NewEntity>?

    @Query("SELECT * FROM NEW_ENTITY WHERE id = :id")
    fun loadById(id: Int): NewEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(newsEntity: List<NewEntity>)

    @Query("DELETE  FROM NEW_ENTITY")
    fun delete()
}