package mx.com.pokemonprueba.ui.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.ui.composables.BottomModalInformationPokemon
import mx.com.pokemonprueba.ui.composables.CustomScaffold
import mx.com.pokemonprueba.ui.composables.ModalDeletePokemon
import mx.com.pokemonprueba.ui.composables.PokemonCard
import mx.com.pokemonprueba.ui.pokemon.view.event.PokemonFavoriteViewEvent
import mx.com.pokemonprueba.ui.pokemon.view.state.PokemonFavoriteViewState

@Composable
fun PokemonFavorites(
    state: PokemonFavoriteViewState,
    navigateTo: (route: String) -> Unit,
    onEvents: (PokemonFavoriteViewEvent) -> Unit
) {

    BottomModalInformationPokemon(
        isVisible = state.isVisibleBottomModal,
        pokemonItem = state.pokemonInformationToShow,
        closeModal = { onEvents(PokemonFavoriteViewEvent.HideModalDeletePokemon) },
        showOptionsButtons = false
    )

    ModalDeletePokemon(
        isVisible = state.isVisibleModalDeletePokemon,
        imagePokemon = state.pokemonInformationToShow?.image ?: "",
        cancel = { onEvents(PokemonFavoriteViewEvent.HideModalDeletePokemon) },
        deletePokemon = {
            state.pokemonInformationToShow?.let {
                onEvents(PokemonFavoriteViewEvent.DeletePokemonDB(it.id))
            }
            onEvents(PokemonFavoriteViewEvent.HideModalDeletePokemon)
        }
    )

    CustomScaffold(
        title = R.string.title_pokemon_favorites,
        navigateTo = { navigateTo(it) }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.pokemons) {
                PokemonCard(pokemon = it) {
                    onEvents(PokemonFavoriteViewEvent.PokemonSelectedToShow(it))
                }
            }
        }
    }
}