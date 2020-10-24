package br.com.teste.sicredi.feature.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.teste.sicredi.feature.StubFactory
import br.com.teste.sicredi.feature.StubFactory.EMAIL
import br.com.teste.sicredi.feature.StubFactory.ERROR_CODE
import br.com.teste.sicredi.feature.StubFactory.EVENT_ID
import br.com.teste.sicredi.feature.StubFactory.NAME
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.detail.ui.EventDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.HttpException

class EventDetailViewModelTest {

    lateinit var viewModel: EventDetailViewModel

    private var observerDetailData: Observer<ViewState<EventDetailData>> = mock()
    private var observerCheckInData: Observer<ViewState<CheckIn>> = mock()
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
    fun `should emit the success check in`() {
        val expected = StubFactory.stubCheckIn()

        viewModel.checkinState.observeForever(observerCheckInData)

        whenever(
            eventDetailSource.sendCheckin(NAME, EMAIL, EVENT_ID)
        ).thenReturn(Observable.just(expected))

        viewModel.sendCheckin(NAME, EMAIL, EVENT_ID)

        Mockito.verify(observerCheckInData).onChanged(ViewState.Success(expected))
    }

    @Test
    fun `should emit code error check in`() {
        val exception = Throwable("Error code $ERROR_CODE")

        viewModel.checkinState.observeForever(observerCheckInData)

        whenever(
            eventDetailSource.sendCheckin(NAME, EMAIL, EVENT_ID)
        ).thenReturn(Observable.error(exception))

        viewModel.sendCheckin(NAME, EMAIL, EVENT_ID)

        Mockito.verify(observerCheckInData).onChanged(ViewState.Failed(exception))
    }

    @Test
    fun `should emit the correct event detail`() {
        val expected = StubFactory.stubEventDetail()
        viewModel.eventDetailstate.observeForever(observerDetailData)

        whenever(eventDetailSource.fetchEventDetail(EVENT_ID)).thenReturn(Observable.just(expected))

        viewModel.fetchEventDetail(EVENT_ID)

        Mockito.verify(eventDetailSource).fetchEventDetail(EVENT_ID)
        Mockito.verify(observerDetailData).onChanged(ViewState.Success(expected))
    }

    @Test
    fun `check event detail error`() {
        viewModel.eventDetailstate.observeForever(observerDetailData)

        whenever(eventDetailSource.fetchEventDetail(EVENT_ID)).thenReturn(Observable.error(httpException))

        viewModel.fetchEventDetail(EVENT_ID)

        Mockito.verify(eventDetailSource).fetchEventDetail(EVENT_ID)
        Mockito.verify(observerDetailData).onChanged(ViewState.Failed(httpException))

    }

}