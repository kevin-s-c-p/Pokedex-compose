package mx.com.pokemonprueba.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mx.com.pokemonprueba.R
import mx.com.pokemonprueba.data.bottom_menu.BottomMenu

@Composable
fun CustomBottomBar(
    showBottomBar: Boolean,
    optionSelected: (route: String) -> Unit
) {
    val menu = listOf(BottomMenu.Home, BottomMenu.Pokemon, BottomMenu.Profile)

    if (showBottomBar) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.05f),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            menu.forEach {
                NavigationBarItem(
                    selected = false,
                    onClick = { optionSelected(it.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = stringResource(id = R.string.content_general_image),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.fillMaxHeight(.6f)
                        )
                    }
                )
            }
        }
    }
}