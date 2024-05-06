package mx.com.pokemonprueba.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import mx.com.pokemonprueba.data.items.PokemonItem

@Composable
fun BottomModalInformationPokemon(
    isVisible: Boolean,
    pokemonItem: PokemonItem?,
    closeModal: () -> Unit,
    savePokemon: () -> Unit
) {
    CustomBottomDialog(isVisible = isVisible, closeModal = closeModal) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .padding(bottom = 25.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {

                CustomImage(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background),
                    image = pokemonItem?.image ?: "",
                    contentScale = ContentScale.Fit
                )
                CustomImage(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.background),
                    image = pokemonItem?.imageBack ?: "",
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            CustomsDoubleButtons(
                textPrimaryButton = "Cancelar",
                textSecondaryButton = "Guardar",
                clickPrimaryButton = { closeModal() },
                clickSecondaryButton = { savePokemon() }
            )
        }
    }
}

@Composable
private fun CustomsDoubleButtons(
    textPrimaryButton: String,
    textSecondaryButton: String,
    clickPrimaryButton: () -> Unit,
    clickSecondaryButton: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomBottomDialog(
    isVisible: Boolean,
    closeModal: () -> Unit,
    content: @Composable () -> Unit
) {
    val bottomModalState = rememberModalBottomSheetState()

    if (isVisible) {
        ModalBottomSheet(
            sheetState = bottomModalState,
            onDismissRequest = closeModal,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}