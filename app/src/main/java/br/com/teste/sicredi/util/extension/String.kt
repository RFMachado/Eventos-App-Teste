package br.com.teste.sicredi.util.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.formatDateToString(): String {
    val date = Date(this.toLong() * 1000)

    return SimpleDateFormat("dd/MM/yyyy, HH:mm 'h'", Locale.getDefault()).format(date)
}