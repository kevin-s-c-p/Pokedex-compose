package mx.com.pokemonprueba.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Upsert
import mx.com.pokemonprueba.data.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pokemonEntity: PokemonEntity): Long
}