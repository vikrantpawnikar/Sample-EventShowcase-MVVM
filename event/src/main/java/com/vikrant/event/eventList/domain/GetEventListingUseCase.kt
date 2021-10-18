package com.vikrant.event.eventList.domain

import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.core.usecase.BaseUseCase
import com.vikrant.event.eventList.repository.EventListRepository
import kotlinx.coroutines.flow.Flow

class GetEventListingUseCase(private val repository: EventListRepository) : BaseUseCase<ResultState<Events>>() {
    public override fun execute(): Flow<ResultState<Events>> {
        return repository.getEvents()
    }
}