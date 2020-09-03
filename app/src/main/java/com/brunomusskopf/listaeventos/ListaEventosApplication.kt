package com.brunomusskopf.listaeventos

import androidx.multidex.MultiDexApplication
import com.brunomusskopf.listaeventos.inject.KoinManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ListaEventosApplication : MultiDexApplication() {

    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(this@ListaEventosApplication)
            // declare modules
            modules(KoinManager.getModulosAplicacao())
        }
    }

}