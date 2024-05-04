package com.invade.universities.data.di

import com.invade.universities.data.UniversityRepositoryImpl
import com.invade.universities.data.network.EduTracAPI
import com.invade.universities.data.room.EduTracDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUniversityRepository(
        apiService: EduTracAPI,
        eduTracDao: EduTracDao,
    ) = UniversityRepositoryImpl(apiService, eduTracDao)

}