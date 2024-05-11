package mx.com.pokemonprueba.ui.profile

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import mx.com.pokemonprueba.MainActivity
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.ui.composables.CustomButton
import mx.com.pokemonprueba.ui.composables.CustomScaffold
import mx.com.pokemonprueba.ui.composables.CustomSwitch
import mx.com.pokemonprueba.ui.profile.view.event.ProfileViewEvent
import mx.com.pokemonprueba.ui.profile.view.state.ProfileViewState

@Composable
fun ProfileScreen(
    state: ProfileViewState,
    onEvent: (ProfileViewEvent) -> Unit,
    navigateTo: (route: String) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.isDarkThemeActivated) {
        val activity = context as MainActivity
        activity.verifyDarkModeSaved()
    }



    CustomScaffold(
        title = R.string.title_profile,
        navigateTo = { navigateTo(it) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = "Num de pokemons: ${state.numOfPokemonsSaved}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Modo oscuro",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            CustomSwitch(
                isChecked = state.isDarkThemeActivated,
                checkedChange = { onEvent(ProfileViewEvent.ChangeDarkMode) }
            )

            Spacer(modifier = Modifier.weight(1f))

            CustomButton(
                textButton = "Eliminar todos los pokemones",
                textColor = MaterialTheme.colorScheme.onPrimary,
                buttonColor = Color.Red
            ) {
                onEvent(ProfileViewEvent.DeleteAllPokemons)
            }
        }
    }
}