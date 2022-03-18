package br.com.syd.marvelcharacters.charactersTest.data

import br.com.syd.marvelcharacters.data.CharacterRepositoryImpl
import br.com.syd.marvelcharacters.data.CharacterService
import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.presentation.CharactersUiModel
import io.mockk.every
import io.mockk.mockk
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
    fun `get character return error`() {
        val error: Throwable = mockk()

        every {
            runBlocking {
                service.getCharacter(
                    any(), any(), any(), any()
                )
            }
        } throws error

        runBlocking {
            val result = repository.getCharacter(0)
            Assert.assertE
        }
    }

}