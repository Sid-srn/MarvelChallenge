package br.com.syd.marvelcharacters.domain

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel

interface CharacterMapper {
    fun reponseToCharacter(characterResponse: CharacterResponse): ArrayList<CharacterModel>
    fun favoriteToCharacter(favoriteCharacters: ArrayList<FavoriteCharacterModel>): ArrayList<CharacterModel>
    fun toFavorite(characterModel: CharacterModel): FavoriteCharacterModel
}

class CharacterMapperImpl() : CharacterMapper {
    override fun reponseToCharacter(characterResponse: CharacterResponse): ArrayList<CharacterModel> {
        val characters = ArrayList<CharacterModel>()

        for (character in characterResponse.data.results) {
            characters.add(
                CharacterModel(
                    id = character.id,
                    name = character.name,
                    description = character.description,
                    picture = character.thumbnail.path + "/standard_medium." + character.thumbnail.extension,
                    character.comics.items.map { it.name },
                    character.series.items.map { it.name },
                    false
                )
            )
        }

        return characters
    }

    override fun favoriteToCharacter(favoriteCharacters: ArrayList<FavoriteCharacterModel>): ArrayList<CharacterModel> {
        val characters = ArrayList<CharacterModel>()

        for (character in favoriteCharacters) {
            characters.add(
                CharacterModel(
                    id = character.id,
                    name = character.name,
                    description = "",
                    picture = character.picture,
                    ArrayList<String>(),
                    ArrayList<String>(),
                    true
                )
            )
        }

        return characters
    }

    override fun toFavorite(characterModel: CharacterModel): FavoriteCharacterModel {
        return FavoriteCharacterModel(
            characterModel.id,
            characterModel.name,
            characterModel.picture
        )
    }

}