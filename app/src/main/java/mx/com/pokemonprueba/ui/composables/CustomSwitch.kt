package mx.com.pokemonprueba.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mx.com.pokemonprueba.ui.theme.ColorSuccess

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    checkedChange: (isChecked: Boolean) -> Unit,
    checkedTrackColor: Color = ColorSuccess,
    uncheckedTrackColor: Color = MaterialTheme.colorScheme.onPrimary,
    checkedBorderColor: Color = MaterialTheme.colorScheme.primary
) {
    Switch(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = { checkedChange(it) },
        colors = SwitchDefaults.colors(
            checkedTrackColor = checkedTrackColor,
            uncheckedTrackColor = uncheckedTrackColor,
            checkedBorderColor = checkedBorderColor,
            uncheckedBorderColor = checkedBorderColor
        ),

    )
}