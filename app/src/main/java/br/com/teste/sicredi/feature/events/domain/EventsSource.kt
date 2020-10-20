package br.com.teste.sicredi.feature.events.domain

import br.com.teste.sicredi.feature.events.domain.entity.Events
import io.reactivex.Single

interface EventsSource {
    fun fetchEventsList() : Single<List<Events>>
}