package br.com.teste.sicredi.feature.detail.domain.entity

data class EventDetailData(
    val id: Int,
    val title: String,
    val description: String,
    val dateString: String,
    val image: String,
    val price: Double,
    val lng: Double,
    val lat: Double
)