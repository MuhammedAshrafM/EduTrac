package com.invade.universities.data.di

import com.invade.universities.data.network.EduTracAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Singleton
    @Provides
    fun provideEduTracAPIService(retrofit: Retrofit.Builder): EduTracAPI =
        retrofit.build().create(EduTracAPI::class.java)

}