package mx.com.pokemonprueba.ui.profile.view.event

sealed class ProfileViewEvent {
    data object ChangeDarkMode: ProfileViewEvent()
    data object DeleteAllPokemons: ProfileViewEvent()
}