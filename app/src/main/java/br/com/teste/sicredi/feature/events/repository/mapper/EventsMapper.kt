package br.com.teste.sicredi.feature.events.repository.mapper

import br.com.teste.sicredi.feature.events.domain.entity.EventData
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import br.com.teste.sicredi.util.extension.formatDateToString

object EventsMapper {
    fun map(payload: List<EventPayload>) = payload.map { map(it) }

    fun map(payload: EventPayload): EventData {
        return EventData(
            id = payload.id,
            title = payload.title,
            description = payload.description,
            dateString = payload.date.formatDateToString(),
            image = payload.image
        )
    }
}