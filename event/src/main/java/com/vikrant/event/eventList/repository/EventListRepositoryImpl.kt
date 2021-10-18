package com.vikrant.event.eventList.repository

import com.vikrant.core.database.EventDataBase
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.eventList.datasource.EventListDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

/**
 * This class will decide from where to get details about events.
 * There are couple of data sources to fetch data from like cache, DB, remote etc.
 */
class EventListRepositoryImpl(
    private val dataSource: EventListDataSource,
    private val dataBase: EventDataBase
) : EventListRepository {
    override fun getEvents(): Flow<ResultState<Events>> {
        val results = dataBase.eventDao().getEventsFromLocalDb()
        return if (results == null) {
            dataSource.getEvents().onEach { value ->
                if (value is ResultState.Success) {
                    dataBase.eventDao().addEvent(value.data)
                }
            }
        } else {
            flow {
                emit(ResultState.Success(results))
            }
        }
    }

}