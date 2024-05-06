package mx.com.pokemonprueba.ui.home.view.state

import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.view_state.ViewState

data class HomeViewState(
    val pokemons: List<PokemonItem> = emptyList(),
    val isLoading: Boolean = false,
    val showModalInformation: Boolean = false,
    val pokemonItemSelected: PokemonItem? = null
): ViewState()
