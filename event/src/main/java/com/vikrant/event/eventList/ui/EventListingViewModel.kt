package com.vikrant.event.eventList.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.eventList.domain.GetEventListingUseCase
import kotlinx.coroutines.launch

class EventListingViewModel constructor(private val getEventListingUseCase: GetEventListingUseCase) : ViewModel() {
    val uiLiveData: LiveData<ResultState<Events>> = getEventListingUseCase.outputFlow.asLiveData()
    fun getEventData() {
        viewModelScope.launch { getEventListingUseCase.launch() }
    }
}