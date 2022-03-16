package br.com.syd.marvelcharacters.module

import br.com.syd.marvelcharacters.data.CharacterService
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.util.RetrofitFactory
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { RetrofitFactory.makeRetrofitService() } bind CharacterService::class
}