package br.com.teste.sicredi.feature.api

import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerApi {
    @GET("/api/events")
    fun getEventsList(): Observable<List<EventPayload>>

    @GET("/api/events/{eventId}")
    fun fetchEventDetail(@Path("eventId") eventId: Float): Completable

    @POST("api/checkin")
    fun checkIn(): Completable
}