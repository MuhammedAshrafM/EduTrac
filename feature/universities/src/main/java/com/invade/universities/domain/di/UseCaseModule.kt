package com.invade.universities.domain.di

import com.invade.universities.data.UniversityRepositoryImpl
import com.invade.universities.domain.usecases.GetLocalUniversitiesUseCase
import com.invade.universities.domain.usecases.GetUniversitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetUniversitiesUseCase(repository: UniversityRepositoryImpl) =
        GetUniversitiesUseCase(repository)

    @Singleton
    @Provides
    fun provideGetLocalUniversitiesUseCase(repository: UniversityRepositoryImpl) =
        GetLocalUniversitiesUseCase(repository)

}