package br.com.syd.marvelcharacters.util

import br.com.syd.marvelcharacters.domain.model.CharacterModel

interface IFavoriteHandle {
    fun saveFavorite(characterModel: CharacterModel)
    fun deleteFavorite(characterModel: CharacterModel)
}
