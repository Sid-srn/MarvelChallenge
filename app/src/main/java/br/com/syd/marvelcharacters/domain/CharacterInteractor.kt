package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.domain.model.CharacterModel

interface CharacterInteractor {
    suspend fun getCharacter(localItens: ArrayList<CharacterModel>): ArrayList<CharacterModel>
    fun getFavorites(): ArrayList<CharacterModel>
    fun saveFavorite(characterModel: CharacterModel)
    fun removeFavorite(characterModel: CharacterModel)
    fun updateLocalFavorites(characters: List<CharacterModel>): ArrayList<CharacterModel>
}

class CharacterInteractorImpl(
    private val repository: CharacterRepository,
    private val mapper: CharacterMapper
) : CharacterInteractor {
    var totalCharacters = 0
    override suspend fun getCharacter(localItens: ArrayList<CharacterModel>): ArrayList<CharacterModel> {
        if (localItens.size <= totalCharacters) {
            val response = repository.getCharacter(localItens.size)
            totalCharacters = response.data.total
            localItens.addAll(addFavorites(mapper.reponseToCharacter(response)))
        }
        return localItens
    }

    override fun getFavorites(): ArrayList<CharacterModel> =
        mapper.favoriteToCharacter(repository.getFavorite())

    override fun saveFavorite(characterModel: CharacterModel) =
        repository.saveFavorite(mapper.toFavorite(characterModel))

    override fun removeFavorite(characterModel: CharacterModel) =
        repository.removeFavorite(mapper.toFavorite(characterModel))

    override fun updateLocalFavorites(characters: List<CharacterModel>): ArrayList<CharacterModel> =
        addFavorites(characters as ArrayList)


    private fun addFavorites(characters: ArrayList<CharacterModel>): ArrayList<CharacterModel> {
        val favorites = repository.getFavorite()

        return characters.mapIndexed { _, character ->
            character.copy(isFavority = (favorites.any { it.id == character.id }))
        } as ArrayList
    }
}