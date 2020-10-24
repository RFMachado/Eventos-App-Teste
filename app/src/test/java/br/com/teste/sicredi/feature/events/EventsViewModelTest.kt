package br.com.teste.sicredi.feature.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.teste.sicredi.feature.StubFactory
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.domain.entity.EventData
import br.com.teste.sicredi.feature.events.ui.EventsViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException

class EventsViewModelTest {
    lateinit var viewModel: EventsViewModel

    private var observerEventsData: Observer<ViewState<List<EventData>>> = mock()
    private val eventsSource: EventsSource = mock()
    private val httpException: HttpException = mock()

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Before
    fun before() {
        viewModel = EventsViewModel(
            source = eventsSource,
            uiScheduler = Schedulers.trampoline()
        )
    }

    @Test
    fun `should emit the correct event items`() {
        val expected = StubFactory.stubEventsList()

        viewModel.state.observeForever(observerEventsData)

        whenever(eventsSource.fetchEventsList()).thenReturn(Observable.just(expected))

        viewModel.fetchEvents()

        Mockito.verify(observerEventsData).onChanged(ViewState.Success(expected))
    }

    @Test
    fun `check events error`() {
        viewModel.state.observeForever(observerEventsData)

        whenever(eventsSource.fetchEventsList()).thenReturn(Observable.error(httpException))

        viewModel.fetchEvents()

        Mockito.verify(observerEventsData).onChanged(ViewState.Failed(httpException))
    }

}