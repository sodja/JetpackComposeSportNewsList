package com.sodja.sportnews.data.remote


import com.sodja.sportnews.domain.model.SportNews
import retrofit2.http.GET

interface SportNewsApi {

    @GET("json-storage/bin/edfefba")
    suspend fun getSportNews(): SportNews

}