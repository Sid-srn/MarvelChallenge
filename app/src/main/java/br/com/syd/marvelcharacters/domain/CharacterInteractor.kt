package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.domain.model.CharacterModel

interface CharacterInteractor {
    suspend fun getCharacter(): List<CharacterModel>
}

class CharacterInteractorImpl(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : CharacterInteractor {
    override suspend fun getCharacter()  =
        mapper.toCharacter(repository.getCharacter())
}