package mx.com.pokemonprueba.data.use_case

import androidx.palette.graphics.Palette
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.single
import mx.com.pokemonprueba.data.items.PokemonAbility
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.ResultPokemonResponse
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.domain.use_case.GetPokemonsUseCase
import mx.com.pokemonprueba.domain.use_case.ImageToBitmapUseCase
import mx.com.pokemonprueba.utils.ApiResponse
import mx.com.pokemonprueba.utils.NetworkResult
import javax.inject.Inject

class GetPokemonsUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonUseCase: GetPokemonUseCase
): GetPokemonsUseCase {
    override suspend fun invoke(): ApiResponse<List<PokemonItem>> {
        return flow {
            pokemonRepository.getPokemons().collect {
                when(it) {
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.error, it.codeError))
                    is NetworkResult.Exception -> emit(NetworkResult.Exception(it.exception))
                    is NetworkResult.Loading -> emit(NetworkResult.Loading(it.isLoading))
                    is NetworkResult.Success -> {
                        emit(NetworkResult.Loading(true))
                        val pokemonList = it.data?.results?.map { pokemon ->
                            getPokemon(pokemon.name)
                        } ?: emptyList()

                        emit(NetworkResult.Success(pokemonList))
                        emit(NetworkResult.Loading(false))
                    }
                }
            }
        }
    }

    private suspend fun getPokemon(name: String): PokemonItem {
        return flow {
            pokemonUseCase.invoke(name).collect {
                when(it) {
                    is NetworkResult.Error -> {}
                    is NetworkResult.Exception -> {}
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        it.data?.let { pokemonResponse ->
                            emit(pokemonResponse)
                        }
                    }
                }
            }
        }.single()
    }
}