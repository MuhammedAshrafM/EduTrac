package com.invade.universities.data

import com.invade.core.network.base.DataState
import com.invade.core.network.base.Status
import com.invade.core.network.base.flowStatus
import com.invade.core.network.base.getResultDao
import com.invade.core.network.base.getResultRestAPI
import com.invade.universities.data.model.University
import com.invade.universities.data.model.UniversityEntityMapperToModel
import com.invade.universities.data.model.UniversityMapperToEntity
import com.invade.universities.data.model.UniversityResponseMapperToModel
import com.invade.universities.data.network.EduTracAPI
import com.invade.universities.data.room.EduTracDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val apiService: EduTracAPI,
    private val eduTracDao: EduTracDao
) : IUniversityRepository {


    override suspend fun getUniversities(countryName: String): Flow<DataState<List<University>>> =
        flowStatus {
            val resultRestAPI = getResultRestAPI(UniversityResponseMapperToModel()) {
                apiService.getUniversities(countryName)
            }

            if (resultRestAPI.status == Status.SUCCESS) {
                // Save universities from the API to local database if the list is not null or empty

                if (resultRestAPI.data?.isNotEmpty() == true)
                    saveUniversities(resultRestAPI.data!!)

            }

            resultRestAPI
        }

    override suspend fun getLocalUniversities(countryName: String): Flow<DataState<List<University>>> =
        flowStatus {
            getResultDao(UniversityEntityMapperToModel()) {
                eduTracDao.getUniversities()
            }
        }

    override suspend fun saveUniversities(articles: List<University>): DataState<List<Long>> {
        // Remove all universities from local database to set the new data again instead of comparing between the new and old lists

        removeUniversities()

        return getResultDao {
            eduTracDao.insertUniversities(
                UniversityMapperToEntity().map(articles)
            )
        }
    }

    override suspend fun removeUniversities(): Flow<DataState<Int>> =
        flowStatus {
            getResultDao { eduTracDao.removeArticles() }
        }

}