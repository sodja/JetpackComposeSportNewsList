package com.sodja.sportnews.domain.repository

import com.sodja.sportnews.commons.Resource
import com.sodja.sportnews.domain.model.News
import kotlinx.coroutines.flow.Flow

interface SportNewsRepository {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getDetailNews(id: Int): Flow<Resource<News>>
}