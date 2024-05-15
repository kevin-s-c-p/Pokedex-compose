package mx.com.pokemonprueba.data.use_case

import android.util.Log
import androidx.palette.graphics.Palette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import mx.com.pokemonprueba.data.items.PokemonAbility
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.domain.use_case.ImageToBitmapUseCase
import mx.com.pokemonprueba.utils.ApiResponse
import mx.com.pokemonprueba.utils.NetworkResult
import javax.inject.Inject

class GetPokemonUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val imageToBitmapUseCase: ImageToBitmapUseCase
): GetPokemonUseCase {
    override suspend fun invoke(idPokemon: Int): ApiResponse<PokemonItem> {
        return flow {
            pokemonRepository.getPokemon(idPokemon).collect {
                when(it) {
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.error, it.codeError))
                    is NetworkResult.Exception -> emit(NetworkResult.Exception(it.exception))
                    is NetworkResult.Loading -> emit(NetworkResult.Loading(it.isLoading))
                    is NetworkResult.Success -> {
                        it.data?.let { pokemonResponse ->
                            val imagePokemonBitmap = imageToBitmapUseCase(pokemonResponse.images.imageFront)
                            val palette = imagePokemonBitmap?.let { Palette.from(it).generate() }
                            val pokemonItem = PokemonItem(
                                id = pokemonResponse.id.toString(),
                                name = pokemonResponse.name,
                                image = pokemonResponse.images.imageFront,
                                imageBack = pokemonResponse.images.imageBack,
                                abilities = pokemonResponse.abilities.map { ability -> PokemonAbility(ability.ability.name) },
                                colorRgbPokemon = palette?.lightVibrantSwatch?.rgb
                            )

                            emit(NetworkResult.Success(pokemonItem))
                        }
                    }
                }
            }
        }
    }


    override suspend fun invoke(namePokemon: String): ApiResponse<PokemonItem> {
        return flow {
            pokemonRepository.getPokemon(namePokemon).collect {
                when(it) {
                    is NetworkResult.Error -> emit(NetworkResult.Error(it.error, it.codeError))
                    is NetworkResult.Exception -> emit(NetworkResult.Exception(it.exception))
                    is NetworkResult.Loading -> emit(NetworkResult.Loading(it.isLoading))
                    is NetworkResult.Success -> {
                        it.data?.let { pokemonResponse ->
                            val imagePokemonBitmap = imageToBitmapUseCase(pokemonResponse.images.imageFront)
                            val palette = imagePokemonBitmap?.let { Palette.from(it).generate() }
                            val pokemonItem = PokemonItem(
                                id = pokemonResponse.id.toString(),
                                name = pokemonResponse.name,
                                image = pokemonResponse.images.imageFront,
                                imageBack = pokemonResponse.images.imageBack,
                                abilities = pokemonResponse.abilities.map { ability -> PokemonAbility(ability.ability.name) },
                                colorRgbPokemon = palette?.lightVibrantSwatch?.rgb
                            )

                            emit(NetworkResult.Success(pokemonItem))
                        }
                    }
                }
            }
        }
    }

}