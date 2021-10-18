package com.vikrant.event.eventList.repository

import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import kotlinx.coroutines.flow.Flow

interface EventListRepository {
    fun getEvents() : Flow<ResultState<Events>>
}