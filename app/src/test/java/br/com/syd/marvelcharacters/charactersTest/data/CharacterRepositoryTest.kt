package br.com.syd.marvelcharacters.charactersTest.data

import br.com.syd.marvelcharacters.data.CharacterRepositoryImpl
import br.com.syd.marvelcharacters.data.CharacterService
import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import br.com.syd.marvelcharacters.presentation.CharactersUiModel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CharacterRepositoryTest {
    private val service: CharacterService = mockk()
    private val uiModel: CharactersUiModel = mockk()

    private val repository = CharacterRepositoryImpl(service, uiModel)

    @Test
    fun `get character return success`() {
        val response = mockk<CharacterResponse>()
        every {
            runBlocking {
                service.getCharacter(
                    any(), any(), any(), any()
                )
            }
        } returns response

        runBlocking {
            val result = repository.getCharacter(0)
            Assert.assertEquals(result, response)
        }

    }

    @Test
    fun `get favorite character return success`() {
        val response = mockk<ArrayList<FavoriteCharacterModel>>()
        every {
            runBlocking {
                uiModel.getFavorite()
            }
        } returns response

        runBlocking {
            val result = repository.getFavorite()
            Assert.assertEquals(result, response)
        }
    }

    @Test
    fun `save favorite character success`() {
        val saveData = mockk<FavoriteCharacterModel>()
        justRun  {
            runBlocking {
                uiModel.saveFavorite(any())
            }
        }

        runBlocking {
            repository.saveFavorite(saveData)
            verify { uiModel.saveFavorite(saveData) }
        }
    }

    @Test
    fun `remove favorite character success`() {
        val removeData = mockk<FavoriteCharacterModel>()
        justRun  {
            runBlocking {
                uiModel.removeFavorite(any())
            }
        }

        runBlocking {
            repository.removeFavorite(removeData)
            verify { uiModel.removeFavorite(removeData) }
        }
    }

}