package com.vikrant.event.eventList.datasource

import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import kotlinx.coroutines.flow.Flow

interface EventListDataSource {
    fun getEvents() : Flow<ResultState<Events>>
}
