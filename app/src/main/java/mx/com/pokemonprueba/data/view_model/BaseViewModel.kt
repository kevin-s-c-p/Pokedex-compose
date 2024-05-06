package mx.com.pokemonprueba.data.view_model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import mx.com.pokemonprueba.data.view_state.ViewState

abstract class BaseViewModel(): ViewModel() {
    private lateinit var _viewState: MutableStateFlow<ViewState>
    private lateinit var viewState: StateFlow<ViewState>

    protected fun initViewState(state: ViewState) {
        _viewState = MutableStateFlow(state)
        viewState = _viewState.asStateFlow()
    }

    protected fun updateViewState(newViewState: ViewState) {
        _viewState.update { newViewState }
    }

    protected fun <T> currentViewState(): T = _viewState.value as T

    fun <T> getState() = viewState as StateFlow<T>

    protected fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    abstract fun isLoading(isLoading: Boolean)
}