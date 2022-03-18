package br.com.syd.marvelcharacters.charactersTest.domain

import br.com.syd.marvelcharacters.charactersTest.mockCharacterModel
import br.com.syd.marvelcharacters.charactersTest.mockCharacterResponse
import br.com.syd.marvelcharacters.charactersTest.mockFavoriteCharacterModel
import br.com.syd.marvelcharacters.domain.CharacterMapperImpl
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import org.junit.Assert
import org.junit.Test

class CharacterMapperTest {
    private val mapper = CharacterMapperImpl()

    @Test
    fun `convert reponse To Character Model List`() {
        mockCharacterResponse
        val result = mapper.reponseToCharacter(mockCharacterResponse)
        Assert.assertNotNull(result)
    }

    @Test
    fun `convert Favorite To Character Model List`() {
        val favorites = arrayListOf<FavoriteCharacterModel>()
        favorites.add(mockFavoriteCharacterModel)
        val result = mapper.favoriteToCharacter(favorites)
        Assert.assertNotNull(result)
        Assert.assertTrue(result.size == 1)

    }

    @Test
    fun `convert Character To Favorite Model List`() {
        val result = mapper.toFavorite(mockCharacterModel)
        Assert.assertNotNull(result)
    }


}