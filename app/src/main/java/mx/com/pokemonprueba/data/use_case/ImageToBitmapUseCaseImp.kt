package mx.com.pokemonprueba.data.use_case

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import mx.com.pokemonprueba.domain.use_case.ImageToBitmapUseCase
import java.net.URL
import javax.inject.Inject

class ImageToBitmapUseCaseImp @Inject constructor(
    private val context: Context
): ImageToBitmapUseCase {
    override suspend fun invoke(urlImage: String): Bitmap? {
        return try {
            val url = URL(urlImage)
            val bitmapImage = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            bitmapImage
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }/*callbackFlow {
        withContext(Dispatchers.IO) {
            try {

                trySend(null)
            } catch (exception: Exception) {
                exception.printStackTrace()
                trySend(null)
            }
        }

        awaitClose {  }
        /*Glide.with(context)
            .asBitmap()
            .load(urlImage)
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    trySend(null)
                    return true
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    trySend(resource)
                    return true
                }
            }).submit()

        awaitClose {  }*/
    }*/
}