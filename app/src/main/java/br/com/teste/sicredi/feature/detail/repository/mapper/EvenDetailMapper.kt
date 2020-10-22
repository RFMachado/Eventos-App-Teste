package br.com.teste.sicredi.feature.detail.repository.mapper

import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.detail.repository.model.CheckInResponse
import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import br.com.teste.sicredi.util.extension.formatDateToString

object EvenDetailMapper {
    fun mapDetail(payload: EventPayload): EventDetailData {
        payload.run {
            return EventDetailData(
                id = id,
                title = title,
                description = description,
                dateString = date.formatDateToString(),
                image = image,
                price = price.toString(),
                lat = lat,
                lng = lng
            )
        }
    }

    fun mapCheckIn(payload: CheckInResponse) = CheckIn(payload.code)
}