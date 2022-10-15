package com.sodja.sportnews.domain.model

import com.sodja.sportnews.data.local.entities.NewEntity

data class News(
    val date: Double?= null,
    val id: Int,
    val thumb: String? = null,
    val url: String?= null,
    val views: Int?= null,
    val author: String?= null,
    val image: String?= null,
    val sport: Sport?= null,
    val teaser: String?= null,
    val title: String?= null
)
fun News.asEntity() = NewEntity(
    uid = null,
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