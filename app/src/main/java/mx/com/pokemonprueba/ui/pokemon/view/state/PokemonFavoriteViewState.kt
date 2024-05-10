package mx.com.pokemonprueba.ui.pokemon.view.state

import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.data.view_state.ViewState

data class PokemonFavoriteViewState(
    val pokemons: List<PokemonItem> = emptyList(),
    val isVisibleModalDeletePokemon: Boolean = false,
    val isVisibleBottomModal: Boolean = false,
    val isLoading: Boolean = false,
    val pokemonInformationToShow: PokemonItem? = null
): ViewState()
