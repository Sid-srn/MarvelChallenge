package br.com.syd.marvelcharacters.presentation

import br.com.syd.marvelcharacters.domain.model.CharacterModel

sealed class CharactersViewEvents {
    class NotifyGetCharactersSuccess(val characters: List<CharacterModel>) : CharactersViewEvents()
    class NotifyGetCharactersException(ex: Exception) : CharactersViewEvents() {

    }
}
