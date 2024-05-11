package mx.com.pokemonprueba.ui.profile

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.local.PokemonDao
import mx.com.pokemonprueba.domain.repository.DataStoreRepository
import mx.com.pokemonprueba.ui.profile.view.event.ProfileViewEvent
import mx.com.pokemonprueba.ui.profile.view.state.ProfileViewState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val dataStoreRepository: DataStoreRepository
): BaseViewModel() {

    init {
        initViewState(ProfileViewState())
        checkTheDarkModeSaved()
    }

    override fun isLoading(isLoading: Boolean) {
        val state: ProfileViewState = currentViewState()
        updateViewState(state.copy(isLoading = isLoading))
    }

    fun onEvent(event: ProfileViewEvent) {
        when(event) {
            ProfileViewEvent.ChangeDarkMode -> changeDarkMode()
            ProfileViewEvent.DeleteAllPokemons -> deleteAllPokemons()
        }
    }

    private fun changeDarkMode() {
        val state: ProfileViewState = currentViewState()
        viewModelScope.launch {
            dataStoreRepository.setIfIsTheDarkTheme(!state.isDarkThemeActivated)
            updateViewState(state.copy(isDarkThemeActivated = !state.isDarkThemeActivated))
        }
    }

    private fun deleteAllPokemons() {
        viewModelScope.launch {
            isLoading(true)
            pokemonDao.deleteAllPokemon()
            isLoading(false)
            updateNumOfPokemonSaved()
        }
    }

    private fun updateNumOfPokemonSaved() {
        val state: ProfileViewState = currentViewState()
        viewModelScope.launch {
            val numOfPokemon = pokemonDao.findNumOfPokemon()
            updateViewState(state.copy(numOfPokemonsSaved = numOfPokemon))
        }
    }

    private fun checkTheDarkModeSaved() {
        val state: ProfileViewState = currentViewState()

        dataStoreRepository.getIfIsTheDarkTheme().onEach {
            updateViewState(state.copy(isDarkThemeActivated = it))
            updateNumOfPokemonSaved()
        }.launchIn(viewModelScope)
    }
}