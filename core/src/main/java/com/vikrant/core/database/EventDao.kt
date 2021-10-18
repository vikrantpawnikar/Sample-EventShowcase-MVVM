package com.vikrant.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vikrant.core.model.Events

@Dao
interface EventDao {
    @Query("SELECT * FROM Events")
    fun getEventsFromLocalDb(): Events?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEvent(events: Events)
}