package mx.com.pokemonprueba.ui.home.view.event

import mx.com.pokemonprueba.data.items.PokemonItem

sealed class HomeViewEvent {
    data object ShowBottomModal: HomeViewEvent()
    data object HideBottomModal: HomeViewEvent()
    data class PokemonSelected(val pokemon: PokemonItem): HomeViewEvent()
}