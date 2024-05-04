package com.invade.universities.domain.usecases

import com.invade.core.network.base.DataState
import com.invade.universities.data.IUniversityRepository
import com.invade.universities.data.model.University
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val repository: IUniversityRepository) {
    suspend operator fun invoke(countryName: String): Flow<DataState<List<University>>> =
        repository.getUniversities(countryName)
}