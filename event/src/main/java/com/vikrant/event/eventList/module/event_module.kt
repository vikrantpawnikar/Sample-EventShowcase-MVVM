package com.vikrant.event.eventList.module
import com.vikrant.event.eventList.datasource.EventListDataSource
import com.vikrant.event.eventList.datasource.EventListRemoteDataSource
import com.vikrant.event.eventList.domain.GetEventListingUseCase
import com.vikrant.event.eventList.repository.EventListRepository
import com.vikrant.event.eventList.repository.EventListRepositoryImpl
import com.vikrant.event.eventList.ui.EventListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var event_module= module {

    factory<EventListDataSource> { EventListRemoteDataSource(get()) }
    
    factory<EventListRepository> { (EventListRepositoryImpl(dataSource = get(), get())) }

    single { GetEventListingUseCase(get()) }

    viewModel { EventListingViewModel(get()) }

}
