package com.antigravity.steptracker.di

import android.content.Context
import androidx.room.Room
import com.antigravity.steptracker.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "step_tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStepDao(database: AppDatabase): com.antigravity.steptracker.data.local.db.dao.StepDao {
        return database.stepDao()
    }
}
