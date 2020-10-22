package br.com.teste.sicredi.feature.detail.ui

import androidx.lifecycle.MutableLiveData
import br.com.teste.sicredi.feature.common.RxViewModel
import br.com.teste.sicredi.feature.common.StateMachine
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.detail.repository.mapper.EvenDetailMapper
import br.com.teste.sicredi.util.extension.toImmutable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class EventDetailViewModel(
    private val source: EventDetailSource,
    private val uiScheduler: Scheduler
): RxViewModel() {
    private val checkinState =
        MutableLiveData<ViewState<CheckIn>>().apply {
            value = ViewState.Loading
        }
    private val eventDetailstate =
        MutableLiveData<ViewState<EventDetailData>>().apply {
            value = ViewState.Loading
        }

    fun getCheckinState() = checkinState.toImmutable()
    fun getEvendDetailState() = eventDetailstate.toImmutable()

    fun fetchEventDetail(eventId: Int) {
        disposables += source.fetchEventDetail(eventId)
            .compose(StateMachine())
            .observeOn(uiScheduler)
            .subscribe(
                { eventDetailstate.value = it },
                { Timber.e(it) }
            )
    }

    fun sendCheckin(name: String, email: String, eventId: Int) {
        disposables += source.sendCheckin(name, email, eventId)
            .compose(StateMachine())
            .observeOn(uiScheduler)
            .subscribe(
                { result ->
                    if(result is ViewState.Success) {
                        if(result.data.code == 200)
                            checkinState.postValue(result)
                        else
                            checkinState.postValue(
                                ViewState.Failed(Throwable("code error ${result.data.code}"))
                            )
                    }
                },
                {
                    checkinState.postValue(ViewState.Failed(it))
                    Timber.e(it)
                }
            )
    }
}