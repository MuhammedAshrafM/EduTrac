package com.invade.universities.data.di

import android.content.Context
import androidx.room.Room
import com.invade.universities.data.room.EduTracDao
import com.invade.universities.data.room.EduTracDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EduTracDBModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): EduTracDatabase {
        return Room.databaseBuilder(
            context,
            EduTracDatabase::class.java,
            "EduTracDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideEduTracDao(eduTracDatabase: EduTracDatabase): EduTracDao {
        return eduTracDatabase.eduTracDao()
    }
}