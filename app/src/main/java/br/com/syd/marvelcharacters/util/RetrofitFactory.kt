package br.com.syd.marvelcharacters.util

import br.com.syd.marvelcharacters.data.CharacterService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    fun makeRetrofitService(): CharacterService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CharacterService::class.java)
    }
}