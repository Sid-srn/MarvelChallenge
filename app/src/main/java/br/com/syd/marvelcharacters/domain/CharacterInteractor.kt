package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel

interface CharacterInteractor {
    suspend fun getCharacter(): List<CharacterModel>
    fun getFavorite(): ArrayList<FavoriteCharacterModel>
    fun saveFavorite(characterModel: FavoriteCharacterModel)
}

class CharacterInteractorImpl(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : CharacterInteractor {
    override suspend fun getCharacter() =
        mapper.toCharacter(repository.getCharacter())

    override fun getFavorite(): ArrayList<FavoriteCharacterModel> =
        repository.getFavorite()


    override fun saveFavorite(characterModel: FavoriteCharacterModel) =
        repository.saveFavorite(characterModel)
}