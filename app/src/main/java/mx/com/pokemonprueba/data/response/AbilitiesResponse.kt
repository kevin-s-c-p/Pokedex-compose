package mx.com.pokemonprueba.data.response

import com.google.gson.annotations.SerializedName

data class AbilitiesResponse(
    @SerializedName("ability")
    val ability: AbilityResponse
)
