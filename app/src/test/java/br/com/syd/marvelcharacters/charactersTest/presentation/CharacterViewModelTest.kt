package br.com.syd.marvelcharacters.charactersTest.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.syd.marvelcharacters.charactersTest.mockCharacterModel
import br.com.syd.marvelcharacters.charactersTest.mockTestCharacter
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.presentation.CharacterViewModel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class CharacterViewModelTest {
    private val interactor: CharacterInteractor = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        setupViewModel()
    }

    @get:Rule
    val coroutineRule = InstantTaskExecutorRule()

    private fun setupViewModel() {
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
    }

    @Test
    fun `get character return success`() = runBlocking {
        setupViewModel()
        val viewModel = CharacterViewModel(interactor)

        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)

    }

    @Test
    fun `get character return exception`() = runBlocking {
        val response = mockk<Exception>()
        every {
            runBlocking {
                interactor.getCharacter(
                    any()
                )
            }
        } throws response
        every {
            runBlocking {
                interactor.getFavorites()
            }
        } returns arrayListOf()
        val viewModel = CharacterViewModel(interactor)

        Assert.assertNull(viewModel.charactersListOb.value)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertNotNull(viewModel.characterExceptionOb.value)

    }

    @Test
    fun `save character return success`() {
        setupViewModel()
        val viewModel = CharacterViewModel(interactor)
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

        viewModel.saveFavorite(saveData)
        verify { interactor.saveFavorite(any()) }
        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)
    }

    @Test
    fun `remove character return success`() {
        setupViewModel()
        val viewModel = CharacterViewModel(interactor)
        val removeData = mockk<CharacterModel>()

        val responseUpdated = ArrayList<CharacterModel>()
        responseUpdated.add(mockCharacterModel)
        responseUpdated.add(mockTestCharacter)

        justRun {
            runBlocking {
                interactor.removeFavorite(any())
            }
        }
        every {
            runBlocking {
                interactor.updateLocalFavorites(
                    any()
                )
            }
        } returns responseUpdated

        viewModel.removeFavorite(removeData)
        verify { interactor.removeFavorite(any()) }
        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)
    }

    @Test
    fun `get more character return success`() = runBlocking {
        setupViewModel()
        val viewModel = CharacterViewModel(interactor)

        viewModel.loadMoreCharacters()

        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)

    }

    @Test
    fun `reload character return success`() = runBlocking {
        setupViewModel()
        val viewModel = CharacterViewModel(interactor)

        viewModel.resetList()

        Assert.assertNotNull(viewModel.charactersListOb.value)
        Assert.assertTrue(viewModel.charactersListOb.value?.size == 2)
        Assert.assertNotNull(viewModel.favoriteCharactersListOb.value)
        Assert.assertTrue(viewModel.favoriteCharactersListOb.value?.size == 1)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}