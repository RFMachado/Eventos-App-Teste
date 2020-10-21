package br.com.teste.sicredi.feature.detail.repository

import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler

class EventDetailRepository(
    private val serverApi: ServerApi,
    private val scheduler: Scheduler
): EventDetailSource {
    override fun fetchEventDetail(eventId: Int): Observable<EventPayload> {
        return serverApi.fetchEventDetail(eventId)
            .subscribeOn(scheduler)
    }

    override fun sendCheckin(name: String, email: String, eventId: Int): Completable {
        return serverApi.checkIn(name = name, email = email, eventId = eventId)
            .subscribeOn(scheduler)
    }
}