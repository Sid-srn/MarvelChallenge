package br.com.syd.marvelcharacters

import android.app.Application
import br.com.syd.marvelcharacters.module.startKoin

class CustomApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin (this@CustomApplication)
    }
}