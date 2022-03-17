package br.com.syd.marvelcharacters.presentation

import android.content.Context
import br.com.syd.marvelcharacters.domain.model.CharacterModel
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface CharactersUiModel {
    fun getFavorite(): ArrayList<FavoriteCharacterModel>
    fun saveFavorite(characterModel: FavoriteCharacterModel)
}

class CharactersUiModelImpl(private val context: Context) : CharactersUiModel {

    override fun saveFavorite(favorite: FavoriteCharacterModel) {
        val favorites = getFavorite()
        favorites.add(favorite)
        val sharedPref =
            context.getSharedPreferences("FAVORITE_KEY", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val json = Gson().toJson(favorites)
            putString("FAVORITE_KEY", json)
            apply()
        }
    }

    override fun getFavorite(): ArrayList<FavoriteCharacterModel> {
        val sharedPref = context.getSharedPreferences("FAVORITE_KEY", Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString("FAVORITE_KEY", null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<ArrayList<FavoriteCharacterModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }
}