package br.com.teste.sicredi.feature.detail.repository

import br.com.teste.sicredi.feature.api.ServerApi
import br.com.teste.sicredi.feature.detail.domain.EventDetailSource
import br.com.teste.sicredi.feature.detail.domain.entity.CheckIn
import br.com.teste.sicredi.feature.detail.domain.entity.EventDetailData
import br.com.teste.sicredi.feature.detail.repository.mapper.EvenDetailMapper
import br.com.teste.sicredi.feature.detail.repository.model.CheckInResponse
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable
import io.reactivex.Scheduler

class EventDetailRepository(
    private val serverApi: ServerApi,
    private val scheduler: Scheduler
): EventDetailSource {
    override fun fetchEventDetail(eventId: Int): Observable<EventDetailData> {
        return serverApi.fetchEventDetail(eventId)
            .subscribeOn(scheduler)
            .map { EvenDetailMapper.mapDetail(it) }
    }

    override fun sendCheckin(name: String, email: String, eventId: Int): Observable<CheckIn> {
        return serverApi.checkIn(name = name, email = email, eventId = eventId)
            .subscribeOn(scheduler)
            .map { EvenDetailMapper.mapCheckIn(it) }
    }
}