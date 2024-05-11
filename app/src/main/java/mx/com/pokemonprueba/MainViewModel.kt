package mx.com.pokemonprueba

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.repository.BaseDataStoreRepository
import mx.com.pokemonprueba.domain.repository.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): BaseViewModel() {

    var darkModeState by mutableStateOf(false)

    init {
        isDarkModeOn()
    }

    override fun isLoading(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    fun isDarkModeOn() {
        viewModelScope.launch {
            dataStoreRepository.getIfIsTheDarkTheme().collect {
                darkModeState = it
            }
        }
    }
}