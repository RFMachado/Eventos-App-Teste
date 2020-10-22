package br.com.teste.sicredi.feature.events.ui

import androidx.lifecycle.MutableLiveData
import br.com.teste.sicredi.feature.common.RxViewModel
import br.com.teste.sicredi.feature.common.StateMachine
import br.com.teste.sicredi.feature.common.ViewState
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.domain.entity.EventData
import br.com.teste.sicredi.util.extension.toImmutable
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class EventsViewModel(
    private val source: EventsSource,
    private val uiScheduler: Scheduler
): RxViewModel() {
    private val state =
        MutableLiveData<ViewState<List<EventData>>>().apply {
        value = ViewState.Loading
    }

    fun getState() = state.toImmutable()

    fun fetchEvents() {
        disposables += source.fetchEventsList()
            .compose(StateMachine())
            .observeOn(uiScheduler)
            .subscribe(
                { state.value = it },
                { Timber.e(it) }
            )
    }
}