package mx.com.pokemonprueba.ui.profile.view.state

import mx.com.pokemonprueba.data.view_state.ViewState

data class ProfileViewState(
    val numOfPokemonsSaved: Int = 0,
    val isLoading: Boolean = false,
    val isDarkThemeActivated: Boolean = false
): ViewState()
