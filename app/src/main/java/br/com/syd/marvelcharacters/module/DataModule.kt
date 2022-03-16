package br.com.syd.marvelcharacters.module

import br.com.syd.marvelcharacters.util.RetrofitFactory
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitFactory.makeRetrofitService() }
}