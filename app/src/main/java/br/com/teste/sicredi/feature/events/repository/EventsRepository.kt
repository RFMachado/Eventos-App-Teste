package br.com.teste.sicredi.feature.events.repository

import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.domain.entity.EventData
import br.com.teste.sicredi.feature.events.repository.mapper.EventsMapper
import io.reactivex.Observable
import io.reactivex.Scheduler

class EventsRepository(
    private val serverApi: ServerApi,
    private val scheduler: Scheduler
): EventsSource {
    override fun fetchEventsList(): Observable<List<EventData>> {
        return serverApi.getEventsList()
            .map { EventsMapper.map(it) }
            .subscribeOn(scheduler)
    }
}