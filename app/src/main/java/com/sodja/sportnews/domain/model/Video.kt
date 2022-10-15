package com.sodja.sportnews.domain.model

data class Video(
    val date: Double,
    val id: Int,
    val sport: Sport,
    val thumb: String,
    val title: String,
    val url: String,
    val views: Int,
)
fun Video.asExternalModel() = News(
    id = id,
    date = date,
    sport = sport,
    thumb = thumb,
    title = title,
    url = url,
    views = views
)