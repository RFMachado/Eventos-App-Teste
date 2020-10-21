package br.com.teste.sicredi.feature.events.repository

import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable
import io.reactivex.Scheduler

class EventsRepository(
    private val serverApi: ServerApi,
    private val scheduler: Scheduler
): EventsSource {
    override fun fetchEventsList(): Observable<List<EventPayload>> {
        return serverApi.getEventsList()
            .subscribeOn(scheduler)
    }
}