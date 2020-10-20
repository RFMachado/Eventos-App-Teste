package br.com.teste.sicredi.di

import br.com.teste.sicredi.R
import br.com.teste.sicredi.feature.api.ServerApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val netModule = module {
    single { providesGson() }
    single { providesOkHttpClient() }

    single { createWebService<ServerApi>(get(), get(), androidContext().getString(R.string.base_url)) }
}

fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

fun providesOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(30, TimeUnit.SECONDS)
    builder.readTimeout(30, TimeUnit.SECONDS)
    builder.writeTimeout(30, TimeUnit.SECONDS)

    return builder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, gson: Gson, url: String): T {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(url)
        .client(okHttpClient)
        .build()
        .create(T::class.java)
}