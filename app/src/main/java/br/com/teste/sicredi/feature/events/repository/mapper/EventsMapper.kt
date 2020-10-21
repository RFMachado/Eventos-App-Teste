package br.com.teste.sicredi.feature.events.repository.mapper

import br.com.teste.sicredi.feature.events.domain.entity.EventsData
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import br.com.teste.sicredi.util.extension.formatDate
import java.util.*

object EventsMapper {
    fun map(payload: List<EventPayload>) = payload.map { map(it) }

    fun map(payload: EventPayload): EventsData {
        return EventsData(
            id = payload.id,
            title = payload.title,
            description = payload.description,
            dateString = payload.date.formatDate(),
            image = payload.image
        )
    }
}