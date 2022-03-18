package br.com.syd.marvelcharacters.charactersTest.presentation

import br.com.syd.marvelcharacters.charactersTest.mockCharacterModel
import br.com.syd.marvelcharacters.charactersTest.mockTestCharacter
import br.com.syd.marvelcharacters.data.CharacterRepository
import br.com.syd.marvelcharacters.data.model.CharacterResponse
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.CharacterInteractorImpl
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.presentation.CharacterViewModel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CharacterViewModelTest {
    private val interactor: CharacterInteractor = mockk()

    @Test
    fun `get character return success`() {
        val response = ArrayList<CharacterModel>()
        response.add(mockCharacterModel)
        response.add(mockTestCharacter)
        every {
            runBlocking {
                interactor.getCharacter(
                    any()
                )
            }
        } returns response

        runBlocking {
            val viewModel = CharacterViewModel(interactor)
            //viewModel.getCharacters()
            //Assert.assertTrue(result.size == 1)
        }

    }

    @Test
    fun `save character return success`() {
        val saveData = mockk<CharacterModel>()

        val responseUpdated = ArrayList<CharacterModel>()
        responseUpdated.add(mockCharacterModel)
        responseUpdated.add(mockTestCharacter)

        justRun {
            runBlocking {
                interactor.saveFavorite(any())
            }
        }
        every {
            runBlocking {
                interactor.updateLocalFavorites(
                    any()
                )
            }
        } returns responseUpdated

        /*viewModel.saveFavorite(saveData)
        verify { interactor.saveFavorite(any()) }
        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)*/


    }
}