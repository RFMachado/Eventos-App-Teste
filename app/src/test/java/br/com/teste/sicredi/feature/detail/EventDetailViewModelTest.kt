package br.com.teste.sicredi.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.teste.sicredi.feature.StubFactory
import br.com.teste.sicredi.feature.StubFactory.EVENT_ID
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
import br.com.teste.sicredi.feature.detail.ui.EventDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class EventDetailViewModelTest {

    lateinit var viewModel: EventDetailViewModel

    private val eventDetailSource: EventDetailSource = mock()
    private val httpException: HttpException = mock()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        viewModel = EventDetailViewModel(
            source = eventDetailSource,
            uiScheduler = Schedulers.trampoline()
        )
    }

    @Test
    fun `should emit the correct event detail`() {
        val provided = StubFactory.stubEventDetail()

        whenever(eventDetailSource.fetchEventDetail(EVENT_ID)).thenReturn(Observable.just(provided))
    }

    @Test
    fun `check event detail error`() {
        whenever(eventDetailSource.fetchEventDetail(EVENT_ID))
            .thenReturn(Observable.error(httpException))
    }

}