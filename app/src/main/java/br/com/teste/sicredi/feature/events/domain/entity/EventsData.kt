package br.com.teste.sicredi.feature.events.domain.entity

data class EventsData(
    val id: Int,
    val title: String,
    val description: String,
    val dateString: String,
    val image: String
)