package com.invade.universities.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.invade.core.network.base.Mapper



@Parcelize
data class University(
    val domains: List<String>,
    val stateProvince: String,
    val name: String,
    val webPages: List<String>,
    val country: String,
    val alphaTwoCode: String
): Parcelable


class UniversityMapperToEntity : Mapper<List<University>, List<UniversityEntity>>() {
    override fun map(input: List<University>): List<UniversityEntity> =
        input.map {
            it.asEntity()
        }
}


fun University.asEntity() = UniversityEntity(
    domains = domains,
    stateProvince = stateProvince,
    name = name,
    webPages = webPages,
    country = country,
    alphaTwoCode = alphaTwoCode
)