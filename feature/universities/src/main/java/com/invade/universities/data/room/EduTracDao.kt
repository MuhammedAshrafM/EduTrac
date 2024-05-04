package com.invade.universities.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.invade.universities.data.model.UniversityEntity

@Dao
interface EduTracDao {

    @Query("SELECT * FROM UniversityEntity")
    suspend fun getUniversities(): List<UniversityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(articles: List<UniversityEntity>): List<Long>

    @Query("DELETE FROM UniversityEntity")
    suspend fun removeArticles(): Int

}