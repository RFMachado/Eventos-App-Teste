package br.com.teste.sicredi.feature.events.domain

import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable

interface EventsSource {
    fun fetchEventsList() : Observable<List<EventPayload>>
}