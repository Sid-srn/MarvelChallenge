package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import retrofit2.Response

interface CharacterMapper {
    fun toCharacter(characterResponse: Any): List<CharacterModel>
}

class CharacterMapperImpl() : CharacterMapper {
    override fun toCharacter(characterResponse: Any): List<CharacterModel> {
        val characters = ArrayList<CharacterModel>()
        //characterResponse.body()?.let {
        val response : CharacterResponse = characterResponse as CharacterResponse
            for (character in characterResponse.data.results) {
                characters.add(
                    CharacterModel(
                        id = character.id,
                        name = character.name,
                        description = character.description,
                        picture = character.thumbnail.path,
                        ArrayList<String>(),
                        ArrayList<String>(),
                        false
                    )
                )
            }
        //}

        return characters
    }

}