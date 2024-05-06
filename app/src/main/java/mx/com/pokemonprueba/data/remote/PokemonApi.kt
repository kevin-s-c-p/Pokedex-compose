package mx.com.pokemonprueba.data.remote

import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/{id}/")
    suspend fun requestGetPokemon(
        @Path("id") idPokemon: String
    ): Response<PokemonResponse>

    @GET("pokemon/{name}/")
    suspend fun requestGetPokemonByName(
        @Path("name") idPokemon: String
    ): Response<PokemonResponse>

    @GET("pokemon/")
    suspend fun requestFirst20Pokemon(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): Response<ResultPokemonResponse>
}