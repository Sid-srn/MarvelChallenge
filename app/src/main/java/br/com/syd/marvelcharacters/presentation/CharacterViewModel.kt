package br.com.syd.marvelcharacters.presentation

import androidx.lifecycle.MutableLiveData
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.BaseViewModel
import kotlinx.coroutines.launch

class CharacterViewModel(private val interactor: CharacterInteractor) : BaseViewModel() {

    //private var state = MutableLiveData<CharactersViewEvents>()
    //val viewState: LiveData<CharactersViewEvents> = state

    val charactersListOb = MutableLiveData<List<CharacterModel>>()
    val favoriteCharactersListOb = MutableLiveData<List<CharacterModel>>()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        launch {
            try {
                val characters = interactor.getCharacter(
                    (charactersListOb.value
                        ?: arrayListOf()) as ArrayList<CharacterModel>
                )
                charactersListOb.value = characters
                favoriteCharactersListOb.value = characters.filter { char -> char.isFavority }
                //state.value = CharactersViewEvents.NotifyGetCharactersSuccess(characters)
            } catch (ex: Exception) {
                //state.value = CharactersViewEvents.NotifyGetCharactersException(ex)
            }
        }
    }

    fun saveFavorite(characterModel: CharacterModel) {
        interactor.saveFavorite(characterModel)
        updateLists()
    }

    fun removeFavorite(characterModel: CharacterModel) {
        interactor.removeFavorite(characterModel)
        updateLists()
    }

    private fun updateLists() {
        val updatedList =
            interactor.updateLocalFavorites(charactersListOb.value ?: listOf())
        charactersListOb.value = updatedList
        favoriteCharactersListOb.value = updatedList.filter { char -> char.isFavority }
    }

    fun loadMoreCharacters() {
        getCharacters()
    }

    fun resetList() {
        charactersListOb.value = ArrayList()
        getCharacters()
    }

}