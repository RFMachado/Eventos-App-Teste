package br.com.teste.sicredi.feature.detail.repository.mapper

import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import br.com.teste.sicredi.util.extension.formatDate

object EvenDetailMapper {
    fun map(payload: EventPayload): EventDetailData {
        payload.run {
            return EventDetailData(
                id = id,
                title = title,
                description = description,
                dateString = date.formatDate(),
                image = image,
                price = price,
                lat = lat,
                lng = lng
            )
        }
    }
}