package mx.com.pokemonprueba.data.repository

import androidx.datastore.preferences.core.Preferences
import mx.com.pokemonprueba.domain.repository.BaseDataStoreRepository

abstract class BaseDataStoreRepositoryImp: BaseDataStoreRepository {
    abstract override suspend fun <T> setValueWithKeyAndValue(preferenceKey: Preferences.Key<T>, value: T)

    abstract override suspend fun <T> getValueWithKey(preferenceKey: Preferences.Key<T>): T?
}