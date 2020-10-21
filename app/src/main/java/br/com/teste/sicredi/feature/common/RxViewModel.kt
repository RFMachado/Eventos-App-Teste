package br.com.teste.sicredi.feature.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class RxViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}