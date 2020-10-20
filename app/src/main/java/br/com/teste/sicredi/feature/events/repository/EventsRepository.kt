package br.com.teste.sicredi.feature.events.repository

import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.domain.entity.Events
import br.com.teste.sicredi.feature.events.repository.mapper.EventsMapper
import io.reactivex.Single

class EventsRepository(private val serverApi: ServerApi): EventsSource {
    override fun fetchEventsList(): Single<List<Events>> {
        return serverApi.getEventsList()
            .map { EventsMapper.map(it) }
    }
}