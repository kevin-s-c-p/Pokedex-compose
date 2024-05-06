package mx.com.pokemonprueba.domain.repository

import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import mx.com.pokemonprueba.utils.ApiResponse

interface PokemonRepository {
    fun getPokemons(): ApiResponse<ResultPokemonResponse>

    fun getPokemon(idPokemon: Int): ApiResponse<PokemonResponse>

    fun getPokemon(namePokemon: String): ApiResponse<PokemonResponse>
}