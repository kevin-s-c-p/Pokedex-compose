package mx.com.pokemonprueba.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.pokemonprueba.data.local.PokemonPruebaDataBase

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providePokemonDao(appDatabase: PokemonPruebaDataBase) = appDatabase.pokemonDao()
}