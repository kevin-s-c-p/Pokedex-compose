package mx.com.pokemonprueba.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    textButton: String,
    buttonColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.background,
    shapeButton: Shape = RoundedCornerShape(15.dp),
    clickButton: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shapeButton,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        onClick = { clickButton() }
    ) {
        Text(
            text = textButton,
            style = MaterialTheme.typography.titleSmall.copy(color = textColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}