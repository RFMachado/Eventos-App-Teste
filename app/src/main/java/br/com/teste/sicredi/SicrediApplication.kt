package br.com.teste.sicredi

import androidx.multidex.MultiDexApplication
import br.com.teste.sicredi.di.appModule
import br.com.teste.sicredi.di.netModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SicrediApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SicrediApplication)
            modules(appModule + netModule)
        }
    }
}