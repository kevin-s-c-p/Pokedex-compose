package mx.com.pokemonprueba.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mx.com.pokemonprueba.ui.theme.ColorSuccess

@Composable
fun CustomsDoubleButtons(
    textPrimaryButton: String,
    textSecondaryButton: String,
    colorPrimaryButton: Color = MaterialTheme.colorScheme.primary,
    colorSecondaryButton: Color = MaterialTheme.colorScheme.primary,
    colorTextPrimaryButton: Color = MaterialTheme.colorScheme.primary,
    colorTextSecondaryButton: Color = MaterialTheme.colorScheme.primary,
    clickPrimaryButton: () -> Unit,
    clickSecondaryButton: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CustomButton(
            modifier = Modifier.weight(1f),
            textButton = textPrimaryButton,
            buttonColor = colorPrimaryButton,
            textColor = colorTextPrimaryButton
        ) {
            clickPrimaryButton()
        }

        CustomButton(
            modifier = Modifier.weight(1f),
            textButton = textSecondaryButton,
            buttonColor = colorSecondaryButton,
            textColor = colorTextSecondaryButton
        ) {
            clickSecondaryButton()
        }
    }
}