package br.com.syd.marvelcharacters.data

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import br.com.syd.marvelcharacters.presentation.CharactersUiModel
import br.com.syd.marvelcharacters.util.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

interface CharacterRepository {
    suspend fun getCharacter(): Any
    fun getFavorite(): ArrayList<FavoriteCharacterModel>
    fun saveFavorite(characterModel: FavoriteCharacterModel)
}

class CharacterRepositoryImpl(
    private val service: CharacterService,
    private val uiModel: CharactersUiModel
) : CharacterRepository {
    override suspend fun getCharacter(): CharacterResponse =
        withContext(IO) {
            service.getCharacter(Constants.TIME_STAMP, Constants.API_KEY, Constants.HASH)
        }

    override fun getFavorite(): ArrayList<FavoriteCharacterModel> = uiModel.getFavorite()

    override fun saveFavorite(characterModel: FavoriteCharacterModel) =
        uiModel.saveFavorite(characterModel)

}