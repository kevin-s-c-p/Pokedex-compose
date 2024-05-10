package mx.com.pokemonprueba.data.items

import mx.com.pokemonprueba.data.entities.PokemonEntity

data class PokemonItem(
    val id: String,
    val image: String,
    val imageBack: String,
    val name: String,
    val abilities: List<PokemonAbility> = emptyList()
) {
    fun toPokemonEntity(): PokemonEntity {
        return PokemonEntity(
            id = this.id.toInt(),
            name = this.name,
            this.image
        )
    }
}
