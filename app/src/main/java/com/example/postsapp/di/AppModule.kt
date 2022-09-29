package com.example.postsapp.di

import android.content.Context
import com.example.postsapp.api.ApiDataSource
import com.example.postsapp.api.ApiService
import com.example.postsapp.data.AppDatabase
import com.example.postsapp.data.AppRepository
import com.example.postsapp.data.PostsDao
import com.example.postsapp.data.UsersDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl("https://jsonplaceholder.typicode.com")
        .build()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideRepository(postsDao: PostsDao, usersDao: UsersDao, apiDataSource: ApiDataSource) =
        AppRepository(postsDao, usersDao, apiDataSource)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiDataSource(apiService: ApiService) = ApiDataSource(apiService)

    @Singleton
    @Provides
    fun providePostsDao(appDatabase: AppDatabase) = appDatabase.postsDao()

    @Singleton
    @Provides
    fun provideUsersDao(appDatabase: AppDatabase) = appDatabase.usersDao()
}