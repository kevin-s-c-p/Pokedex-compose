package mx.com.pokemonprueba.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mx.com.pokemonprueba.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    @StringRes title: Int,
    showReturnIcon: Boolean,
    popUp: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.fillMaxWidth().shadow(5.dp),
        title = { TitleToolbar(title) },
        navigationIcon = { NavigationIcon(showReturnIcon, popUp) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
    )
}

@Composable
private fun TitleToolbar(
    @StringRes title: Int
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
private fun NavigationIcon(showReturnIcon: Boolean, popUp: () -> Unit) {
    if (showReturnIcon) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(.1f).clickable { popUp() },
            tint = MaterialTheme.colorScheme.primary
        )
    }
}