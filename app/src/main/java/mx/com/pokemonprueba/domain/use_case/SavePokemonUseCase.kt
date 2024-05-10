package mx.com.pokemonprueba.domain.use_case

import mx.com.pokemonprueba.data.items.PokemonItem

interface SavePokemonUseCase {
    suspend operator fun invoke(pokemonItem: PokemonItem): Boolean
}