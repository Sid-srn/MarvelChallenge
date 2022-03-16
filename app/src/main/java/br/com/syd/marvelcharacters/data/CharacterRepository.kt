package br.com.syd.marvelcharacters.data

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.util.Constants
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

interface CharacterRepository {
    suspend fun getCharacter(): Any
}

class CharacterRepositoryImpl(private val service: CharacterService) : CharacterRepository {
    override suspend fun getCharacter(): CharacterResponse =
        withContext(IO) {
            service.getCharacter(Constants.TIME_STAMP, Constants.API_KEY, Constants.HASH)
        }


}