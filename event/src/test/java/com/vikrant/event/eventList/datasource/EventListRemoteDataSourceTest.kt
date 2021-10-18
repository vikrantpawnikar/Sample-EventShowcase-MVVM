package com.vikrant.event.eventList.datasource

import com.vikrant.core.retrofitclient.ClientInterface
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventListRemoteDataSourceTest {
    private lateinit var eventListRemoteDataSource: EventListDataSource

    @Mock
    lateinit var api: ClientInterface

    @Before
    fun setUp() {
        eventListRemoteDataSource = EventListRemoteDataSource(api)
    }

}
