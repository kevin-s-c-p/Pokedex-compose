package mx.com.pokemonprueba.domain.repository

interface DataStoreRepository {
    suspend fun setIfIsTheDarkTheme(value: Boolean)

    suspend fun getIfIsTheDarkTheme(): Boolean
}