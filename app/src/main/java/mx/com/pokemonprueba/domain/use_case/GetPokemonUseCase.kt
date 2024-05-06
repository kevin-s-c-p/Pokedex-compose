package mx.com.pokemonprueba.domain.use_case

import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.utils.ApiResponse

interface GetPokemonUseCase {
    suspend operator fun invoke(idPokemon: Int): ApiResponse<PokemonResponse>
    suspend operator fun invoke(namePokemon: String): ApiResponse<PokemonResponse>
}