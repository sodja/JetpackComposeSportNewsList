package com.sodja.sportnews.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.sodja.sportnews.commons.Constants.BASE_URL
import com.sodja.sportnews.commons.Constants.DATABASE_NAME
import com.sodja.sportnews.data.local.AppDatabase
import com.sodja.sportnews.data.local.Converters
import com.sodja.sportnews.data.local.dao.NewsDao
import com.sodja.sportnews.data.remote.SportNewsApi
import com.sodja.sportnews.data.repository.SportNewsRepositoryImpl
import com.sodja.sportnews.domain.repository.SportNewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModules {

    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(): String = BASE_URL


    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
    ): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideSportNewsApi(
        retrofit: Retrofit
    ): SportNewsApi = retrofit.create(SportNewsApi::class.java)

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).addTypeConverter(Converters()).build()
    }

    @Provides
    fun provideNewsDao(appDatabase: AppDatabase): NewsDao = appDatabase.newsDao()

    @Provides
    fun provideSportNewsRepository(
        sportNewsApi: SportNewsApi,
        dao: NewsDao
    ): SportNewsRepository = SportNewsRepositoryImpl(sportNewsApi, dao)


}