package mx.com.pokemonprueba.domain.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import mx.com.pokemonprueba.data.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(pokemonEntity: PokemonEntity): Long

    @Query("SELECT * FROM pokemon")
    suspend fun getAllPokemonSaved(): List<PokemonEntity>

    @Delete
    suspend fun deletePokemon(pokemonEntity: PokemonEntity)

    @Query("DELETE FROM pokemon")
    suspend fun deleteAllPokemon()

    @Query("SELECT COUNT(*) FROM pokemon")
    suspend fun findNumOfPokemon(): Int
}