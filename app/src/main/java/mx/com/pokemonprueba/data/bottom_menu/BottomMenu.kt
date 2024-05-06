package mx.com.pokemonprueba.data.bottom_menu

import androidx.annotation.DrawableRes
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.ui.navigation.Screens.HOME
import mx.com.pokemonprueba.ui.navigation.Screens.POKEMON
import mx.com.pokemonprueba.ui.navigation.Screens.PROFILE

sealed class BottomMenu(
    val route: String,
    @DrawableRes val icon: Int
) {
    data object Home: BottomMenu(route = HOME, R.drawable.ic_home)
    data object Pokemon: BottomMenu(POKEMON, R.drawable.icon_pikachu)
    data object Profile: BottomMenu(PROFILE, R.drawable.ic_perfil)
}