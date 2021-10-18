package com.vikrant.event.eventList.datasource

import com.vikrant.core.retrofitclient.ClientInterface
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EventListRemoteDataSource(private val api: ClientInterface) : EventListDataSource {

    override fun getEvents() = flow {
        emit(ResultState.Progress())
        val response = api.getEvents(BuildConfig.API_KEY)
        emit(ResultState.Success(response))
    }.catch { emit(ResultState.Failure(it)) }.flowOn(Dispatchers.IO)
}