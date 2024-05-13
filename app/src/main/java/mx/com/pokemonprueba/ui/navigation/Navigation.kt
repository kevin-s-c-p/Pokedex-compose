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
import mx.com.pokemonprueba.ui.profile.ProfileScreen
import mx.com.pokemonprueba.ui.profile.ProfileViewModel
import mx.com.pokemonprueba.ui.profile.view.state.ProfileViewState
import mx.com.pokemonprueba.utils.DataNavigation

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
                navigateTo = { navigationController(navController, it) }
            )
        }

        composable(Screens.POKEMON) {
            val viewModel = hiltViewModel<PokemonFavoritesViewModel>()
            val state by viewModel.getState<PokemonFavoriteViewState>().collectAsState()

            PokemonFavorites(
                state,
                navigateTo = { navigationController(navController, it) },
                onEvents = viewModel::onEvent
            )
        }

        composable(Screens.PROFILE) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            val state by viewModel.getState<ProfileViewState>().collectAsState()

            ProfileScreen(
                state = state,
                onEvent = viewModel::onEvent,
                navigateTo = { navigationController(navController, it) }
            )
        }
    }
}

fun navigationController(navController: NavController, dataNavigation: DataNavigation) {
    when(dataNavigation) {
        is DataNavigation.NavigateTo -> {
            navController.navigate(dataNavigation.route) {
                launchSingleTop = true
                restoreState = true
            }
        }
        is DataNavigation.NavigateToCleanStash -> {
            navController.navigate(dataNavigation.route) {
                val destinyStart = dataNavigation.destinyStart.ifEmpty {
                    navController.currentDestination?.route ?: ""
                }
                popUpTo(destinyStart) {
                    saveState = false
                    inclusive = true
                }
                launchSingleTop = true
                restoreState = false
            }
        }
        DataNavigation.NavigateUp -> navController.navigateUp()
    }
}