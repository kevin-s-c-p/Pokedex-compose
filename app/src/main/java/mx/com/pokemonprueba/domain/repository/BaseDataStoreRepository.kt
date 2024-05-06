package mx.com.pokemonprueba.domain.repository

import androidx.datastore.preferences.core.Preferences

interface BaseDataStoreRepository {
    suspend fun <T> setValueWithKeyAndValue(preferenceKey: Preferences.Key<T>, value: T)

    suspend fun <T> getValueWithKey(preferenceKey: Preferences.Key<T>): T?
}