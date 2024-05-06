package mx.com.pokemonprueba.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import mx.com.pokemonprueba.R

@Composable
fun CustomImage(
    image: Any,
    modifier: Modifier = Modifier,
    @StringRes contentDescription: Int = R.string.content_general_image,
    @DrawableRes errorImage: Int = R.drawable.icon_pikachu,
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        model = image,
        contentDescription = stringResource(id = contentDescription),
        error = painterResource(id = errorImage),
        modifier = modifier,
        contentScale = contentScale
    )
}