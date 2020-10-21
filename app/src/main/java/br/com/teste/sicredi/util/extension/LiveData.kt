package br.com.teste.sicredi.util.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.toImmutable(): LiveData<T> = this