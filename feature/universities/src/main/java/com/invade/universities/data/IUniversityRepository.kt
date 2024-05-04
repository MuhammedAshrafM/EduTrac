package com.invade.universities.data

import com.invade.core.network.base.DataState
import com.invade.universities.data.model.University
import kotlinx.coroutines.flow.Flow

interface IUniversityRepository {

     suspend fun getUniversities(countryName: String): Flow<DataState<List<University>>>

     suspend fun getLocalUniversities(countryName: String): Flow<DataState<List<University>>>

     suspend fun saveUniversities(articles: List<University>): DataState<List<Long>>

     suspend fun removeUniversities(): Flow<DataState<Int>>
}