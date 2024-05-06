package mx.com.pokemonprueba

import dagger.hilt.android.lifecycle.HiltViewModel
import mx.com.pokemonprueba.data.view_model.BaseViewModel
import mx.com.pokemonprueba.domain.repository.BaseDataStoreRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {
    override fun isLoading(isLoading: Boolean) {
        TODO("Not yet implemented")
    }
}