package com.sodja.sportnews.domain.model

data class Story(
    val author: String,
    val date: Double,
    val id: Int,
    val image: String,
    val sport: Sport,
    val teaser: String,
    val title: String
)

fun Story.asExternalModel() = News(
    id = id,
    author = author,
    date = date,
    image = image,
    sport = sport,
    teaser = teaser,
    title = title
)