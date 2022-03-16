package br.com.syd.marvelcharacters.module

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.data.CharacterRepositoryImpl
import br.com.syd.marvelcharacters.data.CharacterService
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.CharacterInteractorImpl
import br.com.syd.marvelcharacters.domain.CharacterMapper
import br.com.syd.marvelcharacters.domain.CharacterMapperImpl
import br.com.syd.marvelcharacters.presentation.CharacterViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module{
    viewModel { CharacterViewModel(get()) }
    factory { CharacterInteractorImpl(get(),get()) }
    factory { CharacterRepositoryImpl(get()) }
    factory { CharacterMapperImpl() }
}