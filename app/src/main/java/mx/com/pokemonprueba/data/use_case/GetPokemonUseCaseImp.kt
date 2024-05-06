package mx.com.pokemonprueba.data.use_case

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.utils.ApiResponse
import javax.inject.Inject

class GetPokemonUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository
): GetPokemonUseCase {
    override suspend fun invoke(idPokemon: Int): ApiResponse<PokemonResponse> {
        return pokemonRepository.getPokemon(idPokemon)
    }


    override suspend fun invoke(namePokemon: String): ApiResponse<PokemonResponse> {
        return pokemonRepository.getPokemon(namePokemon)
    }

}