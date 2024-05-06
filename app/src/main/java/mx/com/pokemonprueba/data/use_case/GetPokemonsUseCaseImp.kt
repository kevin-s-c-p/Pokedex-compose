package mx.com.pokemonprueba.data.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.domain.use_case.GetPokemonsUseCase
import mx.com.pokemonprueba.utils.ApiResponse
import javax.inject.Inject

class GetPokemonsUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetPokemonsUseCase {
    override suspend fun invoke(): ApiResponse<ResultPokemonResponse> {
        return pokemonRepository.getPokemons()
    }

}