package br.com.syd.marvelcharacters.charactersTest.domain

import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.CharacterInteractorImpl
import br.com.syd.marvelcharacters.domain.CharacterMapper
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CharacterInteractorTest {
    private val repository: CharacterRepository = mockk()
    private val mapper: CharacterMapper = mockk()

    private val interactor = CharacterInteractorImpl(repository, mapper)

    @Test
    fun `get character return success`() {
        val response = mockk<CharacterResponse>()
        val favoriteResponse = ArrayList<FavoriteCharacterModel>()
        favoriteResponse.add(FavoriteCharacterModel(0, "Character test name", "picture test"))
        val mapedResponse = ArrayList<CharacterModel>()
        mapedResponse.add(
            CharacterModel(
                0,
                "Character test name",
                "Description test",
                "picture test",
                listOf(),
                listOf(),
                false
            )
        )
        every { response.data.total } returns 10
        every {
            runBlocking {
                repository.getCharacter(
                    any()
                )
            }
        } returns response
        every {
            runBlocking {
                repository.getFavorite()
            }
        } returns favoriteResponse
        every {
            runBlocking {
                mapper.reponseToCharacter(
                    any()
                )
            }
        } returns mapedResponse

        runBlocking {
            val result = interactor.getCharacter(ArrayList<CharacterModel>())
            Assert.assertTrue(result.size == 1)
        }
    }
}

