package mx.com.pokemonprueba.ui.home


import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import mx.com.pokemonprueba.data.items.PokemonAbility
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.response.PokemonResponse
import mx.com.pokemonprueba.data.response.PokemonsResponse
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.domain.use_case.GetPokemonsUseCase
import mx.com.pokemonprueba.domain.use_case.ImageToBitmapUseCase
import mx.com.pokemonprueba.domain.use_case.SavePokemonUseCase
import mx.com.pokemonprueba.ui.home.view.event.HomeViewEvent
import mx.com.pokemonprueba.ui.home.view.state.HomeViewState
import mx.com.pokemonprueba.utils.NetworkResult
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val context: Context,
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val savePokemonUseCase: SavePokemonUseCase
): BaseViewModel() {
    init {
        initViewState(HomeViewState())
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch(Dispatchers.IO) {
            getPokemonsUseCase.invoke().collect {
                when(it) {
                    is NetworkResult.Error -> showToast(it.error, context)
                    is NetworkResult.Exception -> it.exception.printStackTrace()
                    is NetworkResult.Loading -> { isLoading(it.isLoading) }
                    is NetworkResult.Success -> {
                        updatePokemonsList(it.data ?: emptyList())
                    }
                }
            }
        }
    }

    fun onEvent(event: HomeViewEvent) {
        when(event) {
            HomeViewEvent.HideBottomModal -> showModal(false)
            HomeViewEvent.ShowBottomModal -> showModal(true)
            is HomeViewEvent.PokemonSelected -> updatePokemon(event.pokemon)
            HomeViewEvent.SavePokemon -> savePokemon()
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

    private fun updatePokemonsList(pokemons: List<PokemonItem>) {
        val state: HomeViewState = currentViewState()
        updateViewState(state.copy(pokemons = pokemons))
    }

    private fun savePokemon() {
        val state: HomeViewState = currentViewState()
        isLoading(true)
        viewModelScope.launch {
            state.pokemonItemSelected?.let {
                val saveSuccess = savePokemonUseCase.invoke(it)
                val messageForToast = if (saveSuccess) "Guardado con exito" else "Hubo un error intente mas tarde"
                showToast(messageForToast, context)
            }
            isLoading(false)
        }
    }
}