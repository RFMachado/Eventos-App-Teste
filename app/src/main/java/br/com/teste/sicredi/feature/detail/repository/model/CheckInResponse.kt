package br.com.teste.sicredi.feature.detail.repository.model

import com.google.gson.annotations.SerializedName

data class CheckInResponse(
    @SerializedName("code")
    val code: String
)