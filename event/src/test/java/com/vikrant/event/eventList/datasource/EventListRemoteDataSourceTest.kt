package com.vikrant.event.eventList.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ClientInterface
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.BuildConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventListRemoteDataSourceTest {
    private lateinit var eventListRemoteDataSource: EventListDataSource

    @Mock
    private val mockEvents = mock<Events>()

    @Mock
    lateinit var api: ClientInterface

    @Before
    fun setUp() {
        eventListRemoteDataSource = EventListRemoteDataSource(api)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSuccessApiResponse() = runBlocking {
        //given
        whenever(api.getEvents(BuildConfig.API_KEY)).thenReturn(mockEvents)
        //when
        val emits  = eventListRemoteDataSource.getEvents().toList()
        //then
        assertEquals(emits, listOf(ResultState.Progress(), ResultState.Success(mockEvents)))
    }

    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun testFailApiResponse() = runBlocking {
        //given
        val exception = Throwable("Exception in service call")
        whenever(api.getEvents(BuildConfig.API_KEY)).thenThrow(exception)
        //when
        val emits  = eventListRemoteDataSource.getEvents().toList()
        //then
        assertEquals(emits, listOf(ResultState.Progress(), ResultState.Failure<Events>(exception)))
    }
}
