package br.com.syd.marvelcharacters.data

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.util.Constants
import retrofit2.Response

interface CharacterRepository {
    fun getCharacter() : Response<CharacterResponse>
}

class CharacterRepositoryImpl( private val service: CharacterService) : CharacterRepository{
    override fun getCharacter(): Response<CharacterResponse> {
        return service.getCharacter(Constants.API_KEY)
    }

}