package mx.com.pokemonprueba.data.items

data class PokemonItem(
    val id: String,
    val image: String,
    val imageBack: String,
    val name: String,
    val abilities: List<PokemonAbility> = emptyList()
)
