package mx.com.pokemonprueba.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mx.com.pokemonprueba.data.use_case.GetPokemonUseCaseImp
import mx.com.pokemonprueba.data.use_case.GetPokemonsUseCaseImp
import mx.com.pokemonprueba.domain.use_case.GetPokemonUseCase
import mx.com.pokemonprueba.domain.use_case.GetPokemonsUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetPokemonUseCase(getPokemonUseCaseImp: GetPokemonUseCaseImp): GetPokemonUseCase

    @Binds
    abstract fun bindGetPokemonsUseCase(getPokemonsUseCaseImp: GetPokemonsUseCaseImp): GetPokemonsUseCase
}