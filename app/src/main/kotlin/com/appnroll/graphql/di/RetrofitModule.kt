package com.appnroll.graphql.di

import com.appnroll.graphql.BuildConfig
import com.appnroll.graphql.data.PokedexBackend
import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory
import io.github.wax911.library.converter.GraphConverter
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit


val retrofitModule = module {

    factory<PokedexBackend> { get<Retrofit>().create(PokedexBackend::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(BACKEND_URL_NAME))
            .client(get())
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .build()
    }

    single<String>(BACKEND_URL_NAME) {
        "https://graphql-pokemon.now.sh"
    }

    single<Converter.Factory> {
        GraphConverter.create(get())
    }

    single<CallAdapter.Factory> {
        SynchronousCallAdapterFactory.create()
    }

    single<OkHttpClient> {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(get(LOGGING_INTERCEPTOR_NAME))
        builder.build()
    }

    single<Interceptor>(LOGGING_INTERCEPTOR_NAME) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        interceptor
    }
}

private const val BACKEND_URL_NAME = "backendUrl"
private const val LOGGING_INTERCEPTOR_NAME = "loggingInterceptor"