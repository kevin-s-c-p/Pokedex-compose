package mx.com.pokemonprueba.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import mx.com.pokemonprueba.R

@Composable
fun Loading(isLoading: Boolean) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.poke_app_loading))

    val lottieAnimation by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isLoading
    )

    if (isLoading) {
        Dialog(
            onDismissRequest = { /*TODO*/ }
        ) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { lottieAnimation },
                    modifier = Modifier.fillMaxSize(.4f)
                )
            }
        }
    }
}