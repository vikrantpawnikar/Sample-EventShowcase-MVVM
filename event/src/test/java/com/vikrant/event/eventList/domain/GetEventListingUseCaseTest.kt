package com.vikrant.event.eventList.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vikrant.core.model.EventDetail
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.eventList.repository.EventListRepository
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetEventListingUseCaseTest {

    private lateinit var useCaseGet: GetEventListingUseCase
    private val eventDetail = EventDetail(
        "type",
        123,
        venue = null,
        dateTime = "dateTime",
        performers = null,
        shortTitle = "shortTitle"
    )
    private val eventDetails = listOf<ResultState<Events>>(
        ResultState.Success(Events(1, listOf(eventDetail)))
    )

    @Mock
    private val repository = mock<EventListRepository>()

    @Before
    fun setUp() {
        useCaseGet = GetEventListingUseCase(repository)

    }

    @Test
    fun doSuccessTest() = runBlocking {
        //given
        whenever(repository.getEvents()).thenReturn(eventDetails.asFlow())
        //when
        val events = useCaseGet.execute().toList()
        //then
        Assert.assertEquals(events, eventDetails)
    }

    @Test
    fun doFailureTest() = runBlocking {

        //given
        val response = listOf(ResultState.Failure<Events>(Exception("Exception occur")))
        whenever(repository.getEvents()).thenReturn(response.asFlow())

        //when
        val events =  useCaseGet.execute().toList()
        //then
        Assert.assertEquals(events, response)
    }

    @Test
    fun doLoadingTest() = runBlocking {

        //given
        val response = listOf(ResultState.Progress<Events>())
        whenever(repository.getEvents()).thenReturn(response.asFlow())

        //when
        val events = useCaseGet.execute().toList()
        //then
        Assert.assertEquals(events, response)
    }

}