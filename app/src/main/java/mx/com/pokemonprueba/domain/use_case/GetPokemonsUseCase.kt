package mx.com.pokemonprueba.domain.use_case

import kotlinx.coroutines.flow.Flow
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import mx.com.pokemonprueba.utils.ApiResponse

interface GetPokemonsUseCase {
    suspend operator fun invoke(): ApiResponse<List<PokemonItem>>
}