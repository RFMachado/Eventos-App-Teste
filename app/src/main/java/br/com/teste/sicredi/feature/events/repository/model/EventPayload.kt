package br.com.teste.sicredi.feature.events.repository.model

import com.google.gson.annotations.SerializedName
import java.util.*

class EventPayload {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("date")
    var date: String = Date().time.toString()

    @SerializedName("image")
    var image: String = ""

    @SerializedName("longitude")
    var lng: Double = 0.0

    @SerializedName("latitude")
    var lat: Double = 0.0

    @SerializedName("price")
    var price: Double = 0.0
}