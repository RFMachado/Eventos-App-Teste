package br.com.teste.sicredi.feature.api

import br.com.teste.sicredi.feature.detail.repository.model.CheckInResponse
import br.com.teste.sicredi.feature.events.repository.model.EventPayload
import io.reactivex.Observable
import retrofit2.http.*

interface ServerApi {
    @GET("/api/events")
    fun getEventsList(): Observable<List<EventPayload>>

    @GET("/api/events/{eventId}")
    fun fetchEventDetail(@Path("eventId") eventId: Int): Observable<EventPayload>

    @FormUrlEncoded
    @POST("api/checkin")
    fun checkIn(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("eventId") eventId: Int
    ): Observable<CheckInResponse>
}