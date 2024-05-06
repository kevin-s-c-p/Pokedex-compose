package mx.com.pokemonprueba.ui.pokemon

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.ui.composables.CustomScaffold

@Composable
fun PokemonFavorites(

    navigateTo: (route: String) -> Unit
) {
    CustomScaffold(
        title = R.string.title_pokemon_favorites
    ) {

    }
}