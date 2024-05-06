package mx.com.pokemonprueba.data.response

data class ResultPokemonResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonsResponse>
)
