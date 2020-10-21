package br.com.teste.sicredi.feature.detail.domain

import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable

interface EventDetailSource {
    fun fetchEventDetail(eventId: Int): Observable<EventPayload>
}