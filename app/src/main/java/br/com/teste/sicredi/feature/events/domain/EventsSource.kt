package br.com.teste.sicredi.feature.events.domain

import br.com.teste.sicredi.feature.events.domain.entity.EventData
import io.reactivex.Observable

interface EventsSource {
    fun fetchEventsList() : Observable<List<EventData>>
}