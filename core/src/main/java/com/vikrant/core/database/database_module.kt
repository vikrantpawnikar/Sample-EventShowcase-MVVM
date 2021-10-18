package com.vikrant.core.database

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val database_module = module {
    fun provideDatabase(application: Application): EventDataBase {
        return Room.databaseBuilder(
            application,
            EventDataBase::class.java,
            "events_database"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    fun provideEventDao(database: EventDataBase): EventDao {
        return database.eventDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideEventDao(get()) }
}
