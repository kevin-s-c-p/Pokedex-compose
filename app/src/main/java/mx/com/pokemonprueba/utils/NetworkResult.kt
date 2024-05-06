package mx.com.pokemonprueba.utils

sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T?): NetworkResult<T>()
    data class Error<out T>(val error: String, val codeError: Int): NetworkResult<T>()
    data class Loading<out T>(val isLoading: Boolean): NetworkResult<T>()
    data class Exception<out T>(val exception: Throwable): NetworkResult<T>()
}
