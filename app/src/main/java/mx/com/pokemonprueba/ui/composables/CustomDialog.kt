package mx.com.pokemonprueba.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
private fun CustomDialog(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    colorDialog: Color = MaterialTheme.colorScheme.primaryContainer,
    closeModal: () -> Unit,
    content: @Composable () -> Unit
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = { closeModal() },
        ) {
            Card(
                modifier = modifier,
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                colors = CardDefaults.cardColors(containerColor = colorDialog)
            ) {
                content()
            }
        }
    }
}

@Composable
fun ModalDeletePokemon(
    isVisible: Boolean,
    imagePokemon: String,
    colorDialog: Int?,
    cancel: () -> Unit,
    deletePokemon: () -> Unit
) {
    val colorDialogToShow = if (colorDialog != null) Color(colorDialog) else MaterialTheme.colorScheme.primaryContainer

    CustomDialog(
        isVisible = isVisible,
        colorDialog = colorDialogToShow,
        closeModal = { cancel() }
    ) {
        Column(
            modifier = Modifier.padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(110.dp),
                contentAlignment = Alignment.Center
            ) {
                CustomImage(
                    modifier = Modifier.fillMaxSize(),
                    image = imagePokemon,
                    contentScale = ContentScale.Fit
                )
            }

            CustomsDoubleButtons(
                textPrimaryButton = "Cancelar",
                textSecondaryButton = "Elimnar",
                colorPrimaryButton = MaterialTheme.colorScheme.onPrimary,
                colorTextPrimaryButton = MaterialTheme.colorScheme.primary,
                colorTextSecondaryButton = MaterialTheme.colorScheme.onPrimary,
                colorSecondaryButton = Color.Red,
                clickPrimaryButton = { cancel() },
                clickSecondaryButton = { deletePokemon() }
            )
        }
    }
}