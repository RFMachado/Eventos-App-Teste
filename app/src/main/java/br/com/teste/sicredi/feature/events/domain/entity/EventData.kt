package br.com.teste.sicredi.feature.events.domain.entity

data class EventData(
    val id: Int,
    val title: String,
    val description: String,
    val dateString: String,
    val image: String
)