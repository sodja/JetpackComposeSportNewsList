package com.sodja.sportnews.data.repository

import com.sodja.sportnews.commons.Resource
import com.sodja.sportnews.commons.Utils.mix
import com.sodja.sportnews.data.local.dao.NewsDao
import com.sodja.sportnews.data.local.entities.toModel
import com.sodja.sportnews.data.remote.SportNewsApi
import com.sodja.sportnews.domain.model.News
import com.sodja.sportnews.domain.model.asEntity
import com.sodja.sportnews.domain.model.asExternalModel
import com.sodja.sportnews.domain.repository.SportNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportNewsRepositoryImpl @Inject constructor(
    private val sportNewsApi: SportNewsApi,
    private val dao: NewsDao
): SportNewsRepository {

    override fun getAllNews(): Flow<Resource<List<News>>> = flow {
        try {
            emit(Resource.Loading)
            val resultList = ArrayList<News>()
            dao.getAll()?.let {list->
                if (list.isNotEmpty()){
                    resultList.addAll(list.map { it.toModel() })
                } else {
                    getSportNewsFromRemote(resultList)
                }
            }?: run {
                getSportNewsFromRemote(resultList)
            }
            emit(Resource.Success(resultList))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getDetailNews(id: Int): Flow<Resource<News>>  = flow{
        try {
            emit(Resource.Loading)
            val responseResearch = dao.loadById(id).toModel()
            emit(Resource.Success(responseResearch))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getSportNewsFromRemote(resultList: ArrayList<News>) {
            val responseApi = sportNewsApi.getSportNews()
            val stories = responseApi.stories.map { it.asExternalModel() }
            val videos = responseApi.videos.map { it.asExternalModel() }
            resultList.addAll(stories.mix(videos))
            saveDataInDataBase(resultList)

    }

    private fun saveDataInDataBase(resultList: ArrayList<News>) {
        dao.delete()
        dao.insertAll(resultList.map { it.asEntity() })
    }

}