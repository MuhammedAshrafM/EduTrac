package com.invade.universities.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.invade.core.network.base.Mapper

@Entity
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true)
    val universityID: Long = 0L,
    val domains: List<String>,
    val stateProvince: String,
    val name: String,
    val webPages: List<String>,
    val country: String,
    val alphaTwoCode: String
)


class UniversityEntityMapperToModel : Mapper<List<UniversityEntity>, List<University>>() {
    override fun map(input: List<UniversityEntity>): List<University> =
        input.map {
            it.asUIModel()
        }
}


fun UniversityEntity.asUIModel() = University(
    domains = domains,
    stateProvince = stateProvince,
    name = name,
    webPages = webPages,
    country = country,
    alphaTwoCode = alphaTwoCode
)