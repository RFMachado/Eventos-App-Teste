package br.com.teste.sicredi.feature.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.teste.sicredi.feature.StubFactory
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.ui.EventsViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventsViewModelTest {
    lateinit var viewModel: EventsViewModel

    private val eventsSource: EventsSource = mock()

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
        val provided = StubFactory.stubEventsList()

        whenever(eventsSource.fetchEventsList()).thenReturn(Observable.just(provided))
    }

}