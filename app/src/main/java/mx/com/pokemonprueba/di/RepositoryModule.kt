package mx.com.pokemonprueba.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.pokemonprueba.data.repository.DataStoreRepositoryImp
import mx.com.pokemonprueba.data.repository.PokemonRepositoryImp
import mx.com.pokemonprueba.domain.repository.DataStoreRepository
import mx.com.pokemonprueba.domain.repository.PokemonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDataStoreRepository(dataStoreRepositoryImp: DataStoreRepositoryImp): DataStoreRepository

    @Binds
    abstract fun bindPokemonRepository(pokemonRepositoryImp: PokemonRepositoryImp): PokemonRepository
}