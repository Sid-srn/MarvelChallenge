package br.com.syd.marvelcharacters.presentation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import br.com.syd.marvelcharacters.domain.CharacterInteractor
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.util.BaseViewModel
import kotlinx.coroutines.launch

class CharacterViewModel(private val interactor: CharacterInteractor) : BaseViewModel() {

    val charactersListOb = MutableLiveData<List<CharacterModel>>()
    val favoriteCharactersListOb = MutableLiveData<List<CharacterModel>>()
    val characterExceptionOb = MutableLiveData<Exception>()

    init {
        getCharacters()
    }

    @VisibleForTesting
    private fun getCharacters() {
        launch {
            try {
                val characters = interactor.getCharacter(
                    (charactersListOb.value
                        ?: arrayListOf()) as ArrayList<CharacterModel>
                )
                charactersListOb.value = characters
                favoriteCharactersListOb.value = characters.filter { char -> char.isFavority }
            } catch (ex: Exception) {
                characterExceptionOb.value = ex
                favoriteCharactersListOb.value = interactor.getFavorites()
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
            interactor.updateLocalFavorites(
                (charactersListOb.value ?: arrayListOf()) as ArrayList<CharacterModel>
            )
        if (updatedList.size > 0) {
            charactersListOb.value = updatedList
            favoriteCharactersListOb.value = updatedList.filter { char -> char.isFavority }
        }else{
            favoriteCharactersListOb.value = interactor.getFavorites()
        }
    }

    fun loadMoreCharacters() {
        getCharacters()
    }

    fun resetList() {
        charactersListOb.value = ArrayList()
        getCharacters()
    }

}