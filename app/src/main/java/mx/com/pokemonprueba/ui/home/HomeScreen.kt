package mx.com.pokemonprueba.ui.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.ui.composables.BottomModalInformationPokemon
import mx.com.pokemonprueba.ui.composables.CustomImage
import mx.com.pokemonprueba.ui.composables.CustomScaffold
import mx.com.pokemonprueba.ui.composables.Loading
import mx.com.pokemonprueba.ui.home.view.event.HomeViewEvent
import mx.com.pokemonprueba.ui.home.view.state.HomeViewState

@Composable
fun HomeScreen(
    state: HomeViewState,
    events: (HomeViewEvent) -> Unit,
    navigateTo: (route: String) -> Unit
) {

    Loading(isLoading = state.isLoading)

    BottomModalInformationPokemon(
        state.showModalInformation,
        state.pokemonItemSelected,
        closeModal = { events(HomeViewEvent.HideBottomModal) },
        savePokemon = {  }
    )

    CustomScaffold(
        title = R.string.title_home,
        navigateTo = { navigateTo(it) }
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.pokemons) { pokemon ->
                PokemonCard(pokemon) {
                    events(HomeViewEvent.PokemonSelected(pokemon))
                    events(HomeViewEvent.ShowBottomModal)

                }
            }
        }
    }
}

@Composable
private fun PokemonCard(pokemon: PokemonItem, pokemonSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { pokemonSelected() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .heightIn(max = 300.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomImage(
                image = pokemon.image,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .fillMaxWidth(.14f)
                    .fillMaxHeight(.17f)
                    .background(MaterialTheme.colorScheme.primary, shape = CircleShape)
                    .clip(CircleShape)
            )

            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
        }
    }
}