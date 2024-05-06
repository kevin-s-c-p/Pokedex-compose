package mx.com.pokemonprueba.data.repository

import mx.com.pokemonprueba.data.remote.PokemonApi
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.utils.ApiResponse
import mx.com.pokemonprueba.utils.BaseRepository
import javax.inject.Inject

class PokemonRepositoryImp @Inject constructor(
    private val api: PokemonApi
): PokemonRepository, BaseRepository() {
    override fun getPokemons(): ApiResponse<ResultPokemonResponse> = requestApi {
        api.requestFirst20Pokemon("0", "20")
    }

    override fun getPokemon(idPokemon: Int): ApiResponse<PokemonResponse> = requestApi {
        api.requestGetPokemon(idPokemon.toString())
    }

    override fun getPokemon(namePokemon: String): ApiResponse<PokemonResponse> = requestApi {
        api.requestGetPokemonByName(namePokemon)
    }
}