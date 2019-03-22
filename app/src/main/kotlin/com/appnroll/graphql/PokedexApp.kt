package com.appnroll.graphql

import android.app.Application
import com.appnroll.graphql.di.retrofitModule
import org.koin.android.ext.android.startKoin


class PokedexApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        initKoin()
    }

    private fun initKoin() {
        startKoin(this, listOf(retrofitModule))
    }

    companion object {

        @JvmStatic
        lateinit var instance: PokedexApp
            private set
    }
}