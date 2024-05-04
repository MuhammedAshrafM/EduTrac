package com.invade.universities.data.network

import com.invade.universities.data.model.UniversityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EduTracAPI {

    @GET("search")
    suspend fun getUniversities(
        @Query("country") countryName: String
    ): Response<List<UniversityResponse>>

}