package mx.com.pokemonprueba.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setIfIsTheDarkTheme(value: Boolean)

    fun getIfIsTheDarkTheme(): Flow<Boolean>
}