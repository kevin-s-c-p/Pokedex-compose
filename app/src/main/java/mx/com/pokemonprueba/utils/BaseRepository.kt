package mx.com.pokemonprueba.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

typealias ApiResponse<T> = Flow<NetworkResult<T>>

abstract class BaseRepository {

    protected fun <R> requestApi(apiCall: suspend () -> Response<R>) = flow<NetworkResult<R>> {
        try {
            emit(NetworkResult.Loading(true))

            val response = apiCall()

            if (response.isSuccessful) {
                emit(NetworkResult.Loading(false))
                emit(NetworkResult.Success(response.body()))
            } else {
                emit(NetworkResult.Loading(false))
                emit(NetworkResult.Error(response.message(), response.code()))
            }
        } catch (exception: Exception) {
            emit(NetworkResult.Exception(exception))
        }
    }
}