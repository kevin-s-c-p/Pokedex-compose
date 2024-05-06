package mx.com.pokemonprueba.ui.pokemon

import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonFavoritesViewModel @Inject constructor(): BaseViewModel() {
    override fun isLoading(isLoading: Boolean) {

    }
}