package mx.com.pokemonprueba.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import mx.com.pokemonprueba.domain.repository.DataStoreRepository
import mx.com.pokemonprueba.utils.DataStoreConstant.DARK_THEME
import mx.com.pokemonprueba.utils.DataStoreConstant.NAME_DATA_STORE
import javax.inject.Inject

private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = NAME_DATA_STORE)

class DataStoreRepositoryImp @Inject constructor(
    private val context: Context
): DataStoreRepository, BaseDataStoreRepositoryImp() {

    override suspend fun setIfIsTheDarkTheme(value: Boolean) {
        val preference = booleanPreferencesKey(DARK_THEME)
        setValueWithKeyAndValue(preference, value)
    }

    override fun getIfIsTheDarkTheme(): Flow<Boolean> {
        return flow {
            emit(
                getValueWithKey(booleanPreferencesKey(DARK_THEME)) ?: false
            )
        }
    }

    override suspend fun <T> getValueWithKey(preferenceKey: Preferences.Key<T>): T? {
        return try {
            val preference = context.datastore.data.first()
            preference[preferenceKey] as T
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    override suspend fun <T> setValueWithKeyAndValue(preferenceKey: Preferences.Key<T>, value: T) {
        context.datastore.edit {
            it[preferenceKey] = value
        }
    }
}