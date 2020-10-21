package br.com.teste.sicredi

import android.app.Application
import br.com.teste.sicredi.di.appModule
import br.com.teste.sicredi.di.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SicrediApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SicrediApplication)
            modules(appModule + netModule)
        }
    }
}