package mx.com.pokemonprueba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.pokemonprueba.ui.home.HomeScreen
import mx.com.pokemonprueba.ui.home.HomeViewModel
import mx.com.pokemonprueba.ui.home.view.state.HomeViewState
import mx.com.pokemonprueba.ui.pokemon.PokemonFavorites

@Composable
fun Navigation(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HOME
    ) {
        composable(Screens.HOME) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.getState<HomeViewState>().collectAsState()

            HomeScreen(
                state = state,
                events = viewModel::onEvent,
                navigateTo = {
                    navController.navigate(it) {
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(Screens.POKEMON) {
            PokemonFavorites(
                navigateTo = {}
            )
        }

        composable(Screens.PROFILE) {

        }
    }
}