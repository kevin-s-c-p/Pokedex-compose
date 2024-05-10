package mx.com.pokemonprueba.data.use_case

import mx.com.pokemonprueba.data.items.PokemonItem
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import mx.com.pokemonprueba.domain.use_case.SavePokemonUseCase
import javax.inject.Inject

class SavePokemonUseCaseImp @Inject constructor(
    private val pokemonRepository: PokemonRepository
): SavePokemonUseCase {
    override suspend fun invoke(pokemonItem: PokemonItem): Boolean {
        return pokemonRepository.savePokemon(pokemonItem)
    }
}