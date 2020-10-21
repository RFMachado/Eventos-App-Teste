package br.com.teste.sicredi.feature.events.repository.model

import com.google.gson.annotations.SerializedName

class EventPayload {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("date")
    lateinit var date: String

    @SerializedName("image")
    lateinit var image: String

    @SerializedName("longitude")
    var lng: Double = 0.0

    @SerializedName("latitude")
    var lat: Double = 0.0

    @SerializedName("price")
    var price: Double = 0.0
}