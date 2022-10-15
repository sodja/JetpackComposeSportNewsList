package com.sodja.sportnews.data.repository

import com.sodja.sportnews.commons.Constants
import com.sodja.sportnews.data.remote.SportNewsApi
import com.sodja.sportnews.domain.model.SportNews
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@RunWith(JUnit4::class)
class SportNewsApiTest {

    private lateinit var newsApi: SportNewsApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
         newsApi = Retrofit.Builder()
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(SportNewsApi::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getNewsFromApiTestWithSuccess() = runTest {
         try {
             val response = newsApi.getSportNews()
             assertThat(response, instanceOf(SportNews::class.java))
         } catch (e: IOException) {
           e.printStackTrace()
        }

    }

}