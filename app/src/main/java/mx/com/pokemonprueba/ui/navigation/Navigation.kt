package mx.com.pokemonprueba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.pokemonprueba.ui.home.HomeScreen
import mx.com.pokemonprueba.ui.home.HomeViewModel
import mx.com.pokemonprueba.ui.home.view.state.HomeViewState
import mx.com.pokemonprueba.ui.pokemon.PokemonFavorites
import mx.com.pokemonprueba.ui.pokemon.PokemonFavoritesViewModel
import mx.com.pokemonprueba.ui.pokemon.view.state.PokemonFavoriteViewState

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
                        popUpTo(Screens.HOME) {
                            saveState = false
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                }
            )
        }

        composable(Screens.POKEMON) {
            val viewModel = hiltViewModel<PokemonFavoritesViewModel>()
            val state by viewModel.getState<PokemonFavoriteViewState>().collectAsState()

            PokemonFavorites(
                state,
                navigateTo = {
                    navController.navigate(it) {
                        popUpTo(Screens.POKEMON) {
                            saveState = false
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                onEvents = viewModel::onEvent
            )
        }

        composable(Screens.PROFILE) {

        }
    }
}