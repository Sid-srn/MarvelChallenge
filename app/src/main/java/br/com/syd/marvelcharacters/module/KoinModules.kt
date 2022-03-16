package br.com.syd.marvelcharacters.module

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(appModules + featureModules)
    }
}

val appModules = listOf(
    dataModule
)

val featureModules = listOf(viewModelsModule)

