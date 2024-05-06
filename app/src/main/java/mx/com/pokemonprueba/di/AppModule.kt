package mx.com.pokemonprueba.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.com.pokemonprueba.data.local.PokemonPruebaDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBaseRoom(app: Application): PokemonPruebaDataBase = Room
        .databaseBuilder(app, PokemonPruebaDataBase::class.java, "pokemon_database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideContextApp(app: Application): Context = app.applicationContext
}