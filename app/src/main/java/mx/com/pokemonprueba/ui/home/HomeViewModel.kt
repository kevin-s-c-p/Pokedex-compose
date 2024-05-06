package mx.com.pokemonprueba.ui.home


import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.PokemonsResponse
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.domain.use_case.GetPokemonsUseCase
import mx.com.pokemonprueba.ui.home.view.event.HomeViewEvent
import mx.com.pokemonprueba.ui.home.view.state.HomeViewState
import mx.com.pokemonprueba.utils.NetworkResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val context: Context,
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getPokemonUseCase: GetPokemonUseCase
): BaseViewModel() {
    init {
        initViewState(HomeViewState())
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            isLoading(true)
            getPokemonsUseCase.invoke().collect {
                when(it) {
                    is NetworkResult.Error -> showToast(it.error, context)
                    is NetworkResult.Exception -> it.exception.printStackTrace()
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> findEveryPokemon(it.data?.results ?: emptyList())
                }
            }
        }
    }

    fun onEvent(event: HomeViewEvent) {
        when(event) {
            HomeViewEvent.HideBottomModal -> showModal(false)
            HomeViewEvent.ShowBottomModal -> showModal(true)
            is HomeViewEvent.PokemonSelected -> updatePokemon(event.pokemon)
        }
    }

    private fun updatePokemon(pokemonSelected: PokemonItem) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(pokemonItemSelected = pokemonSelected))
    }

    private fun showModal(isVisible: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(showModalInformation = isVisible))
    }

    override fun isLoading(isLoading: Boolean) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(isLoading = isLoading))
    }

    private fun findEveryPokemon(pokemonFind: List<PokemonsResponse>) {
        viewModelScope.launch {
            val pokemons: MutableList<PokemonItem> = mutableListOf()
            pokemonFind.forEach {
                getPokemonUseCase.invoke(it.name).collect {
                       when(it) {
                           is NetworkResult.Error -> showToast(it.error, context)
                           is NetworkResult.Exception -> it.exception.printStackTrace()
                           is NetworkResult.Loading -> {}
                           is NetworkResult.Success -> {
                               it.data?.let { pokemon ->
                                   val pokemonItem = PokemonItem(
                                       id = pokemon.id.toString(),
                                       name = pokemon.name,
                                       image = pokemon.images.imageFront,
                                       imageBack = pokemon.images.imageBack
                                   )

                                   pokemons.add(pokemonItem)
                               }
                           }
                       }
                }
            }

            isLoading(false)
            updatePokemonsList(pokemons)
        }
    }

    private fun updatePokemonsList(pokemons: List<PokemonItem>) {
        val state: HomeViewState = currentViewState()

        updateViewState(state.copy(pokemons = pokemons))
    }
}