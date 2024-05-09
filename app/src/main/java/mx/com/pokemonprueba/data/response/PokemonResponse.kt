package mx.com.pokemonprueba.data.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val images: SpritesResponse,
    @SerializedName("abilities")
    val abilities: List<AbilitiesResponse>
)
