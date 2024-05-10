package mx.com.pokemonprueba.ui.pokemon.view.event

import mx.com.pokemonprueba.data.items.PokemonItem

sealed class PokemonFavoriteViewEvent {
    data object ShowModalDeletePokemon: PokemonFavoriteViewEvent()
    data object HideModalDeletePokemon: PokemonFavoriteViewEvent()
    data class DeletePokemonDB(val idPokemon: String): PokemonFavoriteViewEvent()
    data object ShowBottomModalPokemon: PokemonFavoriteViewEvent()
    data object HideBottomModalPokemon: PokemonFavoriteViewEvent()
    data class PokemonSelectedToShow(val pokemonItem: PokemonItem?): PokemonFavoriteViewEvent()
}