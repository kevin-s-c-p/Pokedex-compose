package mx.com.pokemonprueba.ui.composables

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

@Composable
fun CustomScaffold(
    @StringRes title: Int,
    showBottomMenu: Boolean = true,
    showReturnIcon: Boolean = false,
    popUp: () -> Unit = {},
    navigateTo: (route: String) -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                title,
                showReturnIcon,
                popUp = { popUp() }
            )
        },
        bottomBar = {
            CustomBottomBar(
                showBottomBar = showBottomMenu
            ) { route ->
                navigateTo(route)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content()
        }
    }
}