package com.vikrant.event.eventList.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vikrant.core.database.EventDao
import com.vikrant.core.database.EventDataBase
import com.vikrant.core.model.EventDetail
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.eventList.datasource.EventListDataSource
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventListRepositoryImplTest {
    private lateinit var repository: EventListRepository
    private val eventDetail = EventDetail(
        "type",
        123,
        venue = null,
        dateTime = "dateTime",
        performers = null,
        shortTitle = "shortTitle"
    )
    private val eventDetails = listOf<ResultState<Events>>(
        ResultState.Success(Events(Math.random().toInt(), listOf(eventDetail)))
    )

    @Mock
    private val dataSource = mock<EventListDataSource>()

    @Mock
    private val dataBase = mock<EventDataBase>()
    @Mock
    private val dao = mock<EventDao>()

    @Before
    fun setUp() {
        repository = EventListRepositoryImpl(dataSource, dataBase)
        whenever(dataBase.eventDao()).thenReturn(dao)
    }

    @Test
    fun doSuccessTest() = runBlocking {
        //given
        whenever(dataSource.getEvents()).thenReturn(eventDetails.asFlow())
        //when
        val events = repository.getEvents().toList()
        //then
        assertEquals(events, eventDetails)
    }

    @Test
    fun doFailureTest() = runBlocking {

        //given
        val response = listOf(ResultState.Failure<Events>(Exception("Exception occur")))
        whenever(dataSource.getEvents()).thenReturn(response.asFlow())

        //when
        val events = repository.getEvents().toList()
        //then
        assertEquals(events, response)
    }

    @Test
    fun doLoadingTest() = runBlocking {

        //given
        val response = listOf(ResultState.Progress<Events>())
        whenever(dataSource.getEvents()).thenReturn(response.asFlow())

        //when
        val events = repository.getEvents().toList()
        //then
        assertEquals(events, response)
    }

    @Test
    fun doSuccessFromLocalCacheTest() = runBlocking {
        //given
        whenever(dao.getEventsFromLocalDb()).thenReturn(Events(Math.random().toInt(), listOf(eventDetail)))
        //when
        val events = repository.getEvents().toList()
        //then
        assertEquals(events, eventDetails)
    }
}