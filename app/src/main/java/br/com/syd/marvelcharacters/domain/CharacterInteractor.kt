package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.domain.model.CharacterModel

interface CharacterInteractor {
    suspend fun getCharacter(): List<CharacterModel>
    fun getOffLineFavorite(): List<CharacterModel>
    fun saveFavorite(characterModel: CharacterModel)
    fun removeFavorite(characterModel: CharacterModel)
}

class CharacterInteractorImpl(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : CharacterInteractor {
    override suspend fun getCharacter() =
        addFavorites(mapper.reponseToCharacter(repository.getCharacter()))

    override fun getOffLineFavorite(): List<CharacterModel> =
        mapper.favoriteToCharacter(repository.getFavorite())

    override fun saveFavorite(characterModel: CharacterModel) =
        repository.saveFavorite(mapper.toFavorite(characterModel))

    override fun removeFavorite(characterModel: CharacterModel) =
        repository.removeFavorite(mapper.toFavorite(characterModel))

    private fun addFavorites(characters: List<CharacterModel>): List<CharacterModel> {
        val favorites = repository.getFavorite()

        return characters.mapIndexed { _, character ->
            if (favorites.any { it.id == character.id }) character.copy(isFavority = true) else character
        }
    }
}