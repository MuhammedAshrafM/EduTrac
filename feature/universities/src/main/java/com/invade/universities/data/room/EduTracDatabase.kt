package com.invade.universities.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.invade.universities.data.model.UniversityEntity

@Database(entities = [UniversityEntity::class], version = 2, exportSchema = false)
@TypeConverters(StringConverter::class)
abstract class EduTracDatabase: RoomDatabase() {

    abstract fun eduTracDao(): EduTracDao

}