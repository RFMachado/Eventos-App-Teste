package br.com.teste.sicredi.feature.detail.domain

import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.detail.repository.model.CheckInResponse
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable

interface EventDetailSource {
    fun fetchEventDetail(eventId: Int): Observable<EventDetailData>
    fun sendCheckin(name: String, email: String, eventId: Int): Observable<CheckIn>
}