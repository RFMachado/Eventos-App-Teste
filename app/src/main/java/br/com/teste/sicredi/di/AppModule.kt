package br.com.teste.sicredi.di

import br.com.teste.sicredi.feature.events.domain.EventsSource
import br.com.teste.sicredi.feature.events.repository.EventsRepository
import br.com.teste.sicredi.feature.events.ui.EventsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<EventsSource> {
        EventsRepository(
            serverApi = get(),
            scheduler = Schedulers.io()
        )
    }

    viewModel {
        EventsViewModel(
            source = get(),
            uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}