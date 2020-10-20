package br.com.teste.sicredi.feature.events.repository.mapper

import br.com.teste.sicredi.feature.events.domain.entity.Events
import br.com.teste.sicredi.feature.events.repository.model.EventPayload

object EventsMapper {
    fun map(payload: List<EventPayload>) = payload.map { map(it) }

    fun map(payload: EventPayload): Events {
        return Events(
            id = payload.id,
            title = payload.title,
            description = payload.description,
            date = payload.date,
            image = payload.image
        )
    }
}