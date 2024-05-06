package mx.com.pokemonprueba.data.response

import com.google.gson.annotations.SerializedName

data class PokemonsResponse(
    val name: String,
    @SerializedName("url")
    val urlInformationPokemon: String
)
