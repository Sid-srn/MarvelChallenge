package br.com.syd.marvelcharacters.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import br.com.syd.marvelcharacters.util.BaseViewModel
import kotlinx.coroutines.launch

class CharacterViewModel(private val interactor: CharacterInteractor) : BaseViewModel() {

    private var state = MutableLiveData<CharactersViewEvents>()
    val viewState: LiveData<CharactersViewEvents> = state

    val charactersListOb = MutableLiveData<List<CharacterModel>>()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        launch {
            try {
                val characters = interactor.getCharacter()
                charactersListOb.value = characters
                state.value = CharactersViewEvents.NotifyGetCharactersSuccess(characters)
            } catch (ex: Exception) {
                state.value = CharactersViewEvents.NotifyGetCharactersException(ex)
            }
        }
    }

    fun saveFavorite(characterModel: FavoriteCharacterModel){
        interactor.saveFavorite(characterModel)
    }

    fun reloadCharacters() {
        launch {
            try {
                val characters = interactor.getCharacter()
                state.value = CharactersViewEvents.NotifyReloadCharactersSuccess(characters)
            } catch (ex: Exception) {
                state.value = CharactersViewEvents.NotifyGetCharactersException(ex)
            }
        }
    }

}