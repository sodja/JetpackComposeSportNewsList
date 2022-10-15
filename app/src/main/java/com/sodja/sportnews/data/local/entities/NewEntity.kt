package com.sodja.sportnews.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sodja.sportnews.domain.model.News
import com.sodja.sportnews.domain.model.Sport

@Entity(tableName = "new_entity")
data class NewEntity (
        @PrimaryKey(autoGenerate = true)
        val uid: Int?,
        val id: Int,
        val date: Double?,
        val thumb: String?,
        val url: String?,
        val views: Int?,
        val author: String?,
        val image: String?,
        val sport: Sport?,
        val teaser: String?,
        val title: String?
        )

fun NewEntity.toModel() = News(
        id = id,
        date = date,
        sport = sport,
        thumb = thumb,
        title = title,
        url = url,
        views = views,
        author = author,
        image = image,
        teaser = teaser,
)