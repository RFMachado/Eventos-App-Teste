package br.com.teste.sicredi.feature.events.repository.model

import com.google.gson.annotations.SerializedName
import java.util.*

class EventPayload {
    @SerializedName("id")
    var id: Double = 0.0

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("description")
    lateinit var description: String

    @SerializedName("date")
    lateinit var date: String

    @SerializedName("image")
    lateinit var image: String

}