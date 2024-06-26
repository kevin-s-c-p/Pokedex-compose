package mx.com.pokemonprueba.ui.pokemon

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.local.PokemonDao
import mx.com.pokemonprueba.ui.pokemon.view.event.PokemonFavoriteViewEvent
import mx.com.pokemonprueba.ui.pokemon.view.state.PokemonFavoriteViewState
import javax.inject.Inject

@HiltViewModel
class PokemonFavoritesViewModel @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val context: Context
): BaseViewModel() {

    init {
        initViewState(PokemonFavoriteViewState())
        loadPokemonSaved()
    }

    private fun loadPokemonSaved() {
        val state: PokemonFavoriteViewState = currentViewState()

        viewModelScope.launch {
            isLoading(true)
            val pokemonsItem = pokemonDao.getAllPokemonSaved().map {
                PokemonItem(
                    id = it.id.toString(),
                    name = it.name,
                    image = it.image,
                    colorRgbPokemon = it.colorRgb
                )
            }

            updateViewState(state.copy(pokemons = pokemonsItem, isLoading = false))

        }
    }

    override fun isLoading(isLoading: Boolean) {
        val state: PokemonFavoriteViewState = currentViewState()
        updateViewState(state.copy(isLoading = isLoading))
    }

    fun onEvent(event: PokemonFavoriteViewEvent) {
        when(event) {
            is PokemonFavoriteViewEvent.DeletePokemonDB -> deletePokemonDB(event.idPokemon)
            is PokemonFavoriteViewEvent.PokemonSelectedToShow -> pokemonSelectedToShow(event.pokemonItem)
            PokemonFavoriteViewEvent.HideBottomModalPokemon -> isVisibleBottomModal(false)
            PokemonFavoriteViewEvent.HideModalDeletePokemon -> isVisibleModalToDelete(false)
            PokemonFavoriteViewEvent.ShowBottomModalPokemon -> isVisibleBottomModal(true)
            PokemonFavoriteViewEvent.ShowModalDeletePokemon -> isVisibleModalToDelete(true)
        }
    }

    private fun isVisibleBottomModal(isVisible: Boolean) {
        val state: PokemonFavoriteViewState = currentViewState()
        updateViewState(state.copy(isVisibleBottomModal = isVisible))
    }

    private fun isVisibleModalToDelete(isVisible: Boolean) {
        val state: PokemonFavoriteViewState = currentViewState()
        updateViewState(state.copy(isVisibleModalDeletePokemon = isVisible))
    }

    private fun pokemonSelectedToShow(pokemonItem: PokemonItem?) {
        val state: PokemonFavoriteViewState = currentViewState()
        updateViewState(state.copy(pokemonInformationToShow = pokemonItem))
        isVisibleModalToDelete(true)
    }

    private fun deletePokemonDB(pokemonId: String) {
        val state: PokemonFavoriteViewState = currentViewState()
        val pokemonToDelete = state.pokemons.find { it.id == pokemonId }
        viewModelScope.launch {
            pokemonToDelete?.let {
                pokemonDao.deletePokemon(it.toPokemonEntity())
            }

            val pokemonDBUpdate = pokemonDao.getAllPokemonSaved().map {
                PokemonItem(
                    id = it.id.toString(),
                    name = it.name,
                    image = it.image
                )
            }
            updateViewState(state.copy(pokemons = pokemonDBUpdate, isVisibleModalDeletePokemon = false))
        }
    }
}