package br.com.syd.marvelcharacters.presentation

import android.content.Context
import br.com.syd.marvelcharacters.domain.model.FavoriteCharacterModel
import br.com.syd.marvelcharacters.util.Constants.FAVORITE_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

interface CharactersUiModel {
    fun getFavorite(): ArrayList<FavoriteCharacterModel>
    fun saveFavorite(characterModel: FavoriteCharacterModel)
    fun removeFavorite(characterModel: FavoriteCharacterModel)
}

class CharactersUiModelImpl(private val context: Context) : CharactersUiModel {

    override fun saveFavorite(favorite: FavoriteCharacterModel) {
        val favorites = getFavorite()
        if (!favorites.contains(favorite)) {
            favorites.add(favorite)
            SaveList(favorites)
        }
        return
    }

    override fun removeFavorite(favorite: FavoriteCharacterModel) {
        val favorites = getFavorite()
        if (favorites.contains(favorite))
            favorites.remove(favorite)
        SaveList(favorites)
    }

    override fun getFavorite(): ArrayList<FavoriteCharacterModel> {
        val sharedPref = context.getSharedPreferences(FAVORITE_KEY, Context.MODE_PRIVATE)
        val gsonValue = sharedPref?.getString(FAVORITE_KEY, null)
        if (gsonValue != null) {
            val itemType: Type = object : TypeToken<ArrayList<FavoriteCharacterModel>>() {}.type
            return Gson().fromJson(gsonValue, itemType)
        }
        return ArrayList()
    }

    private fun SaveList(favorites: ArrayList<FavoriteCharacterModel>) {
        val sharedPref =
            context.getSharedPreferences(FAVORITE_KEY, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            val json = Gson().toJson(favorites)
            putString(FAVORITE_KEY, json)
            apply()
        }
    }
}