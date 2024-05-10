package mx.com.pokemonprueba.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.com.pokemonprueba.data.entities.PokemonEntity
import mx.com.pokemonprueba.domain.local.PokemonDao

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class PokemonPruebaDataBase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}