package com.invade.universities.data.model

import com.google.gson.annotations.SerializedName
import com.invade.core.network.base.Mapper

data class UniversityResponse(
    @SerializedName("domains")
    val domains: List<String>? = null,

    @SerializedName("state-province")
    val stateProvince: String? = null,

    val name: String? = null,

    @SerializedName("web_pages")
    val webPages: List<String>? = null,

    val country: String? = null,

    @SerializedName("alpha_two_code")
    val alphaTwoCode: String? = null

)

class UniversityResponseMapperToModel : Mapper<List<UniversityResponse>, List<University>>() {
    override fun map(input: List<UniversityResponse>): List<University> =
        input.map {
            it.asUIModel()
        }
}


fun UniversityResponse.asUIModel() = University(
    domains = domains ?: listOf(),
    stateProvince = stateProvince ?: "",
    name = name ?: "",
    webPages = webPages ?: listOf(),
    country = country ?: "",
    alphaTwoCode = alphaTwoCode ?: ""
)