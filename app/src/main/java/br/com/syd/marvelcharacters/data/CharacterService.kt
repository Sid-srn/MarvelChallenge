package br.com.syd.marvelcharacters.data

import br.com.syd.marvelcharacters.data.model.CharacterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {
    @GET("characters")
    suspend fun getCharacter(
        @Query("offset") offSet : Int,
        @Query("ts") timestamp : String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): CharacterResponse

}