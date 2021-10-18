package com.vikrant.event.eventList.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.vikrant.core.model.EventDetail
import com.vikrant.core.model.Events
import com.vikrant.core.retrofitclient.ResultState
import com.vikrant.event.eventList.TestCoroutineRule
import com.vikrant.event.eventList.domain.GetEventListingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class EventListingViewModelTest {
    private var result: List<ResultState<Events>> = emptyList()
    private lateinit var viewModel: EventListingViewModel

    private val eventDetail = EventDetail(
        "type",
        123,
        venue = null,
        dateTime = "dateTime",
        performers = null,
        shortTitle = "shortTitle"
    )
    private val eventDetailsSuccessResponse = ResultState.Success(Events(Math.random().toInt(), listOf(eventDetail)))

    private var useCase = mock<GetEventListingUseCase> {
        on(it.outputFlow) doReturn result.asFlow()
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var eventObserver: Observer<ResultState<Events>>

    @Before
    fun setUp() {
        prepareData()
        viewModel = EventListingViewModel(useCase)
    }

    private fun prepareData() {
        result = listOf(ResultState.Progress(), eventDetailsSuccessResponse)
    }

    @Test
    fun testProgressOutput() = runBlockingTest {
        //given
        Mockito.doReturn(listOf(ResultState.Progress<Events>()).asFlow())
            .`when`(useCase)
            .outputFlow
        // when
        viewModel.getEventData()
        viewModel.uiLiveData.observeForever(eventObserver)

        // then
        verify(useCase).launch()
        //check data
        viewModel.uiLiveData.removeObserver(eventObserver)
    }

}