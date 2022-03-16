package br.com.syd.marvelcharacters.data

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("characters")
    fun getCharacter(
        @Query("apikey") apikey: String
    ): Response<CharacterResponse>
}