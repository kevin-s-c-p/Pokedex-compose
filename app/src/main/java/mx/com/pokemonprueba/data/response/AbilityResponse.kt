package mx.com.pokemonprueba.data.response

import com.google.gson.annotations.SerializedName

data class AbilityResponse(
    @SerializedName("name")
    val name: String
)
