package br.com.teste.sicredi.feature.detail.ui

import androidx.lifecycle.MutableLiveData
import br.com.teste.sicredi.feature.common.RxViewModel
import br.com.teste.sicredi.feature.common.StateMachine
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
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
    private val state =
        MutableLiveData<ViewState<EventDetailData>>().apply {
            value = ViewState.Loading
        }

    fun getState() = state.toImmutable()

    fun fetchEventDetail(eventId: Int) {
        disposables += source.fetchEventDetail(eventId)
            .map { EvenDetailMapper.map(it) }
            .compose(StateMachine())
            .observeOn(uiScheduler)
            .subscribe(
                { state.value = it },
                { Timber.e(it) }
            )
    }
}