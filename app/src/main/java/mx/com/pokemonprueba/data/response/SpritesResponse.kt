package mx.com.pokemonprueba.data.response

import com.google.gson.annotations.SerializedName

data class SpritesResponse(
    @SerializedName("back_default")
    val imageBack: String,
    @SerializedName("front_default")
    val imageFront: String
)
