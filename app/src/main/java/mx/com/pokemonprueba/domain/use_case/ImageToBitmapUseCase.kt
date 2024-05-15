package mx.com.pokemonprueba.domain.use_case

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow

interface ImageToBitmapUseCase {
    suspend operator fun invoke(urlImage: String):Bitmap?
}