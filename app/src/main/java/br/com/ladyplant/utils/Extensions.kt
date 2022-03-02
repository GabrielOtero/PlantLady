package br.com.ladyplant.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException


suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): Result<T, ResultError.NetworkError> {
    return withContext(dispatcher) {
        flow {
            emit(
                try {
                    mapResponse(apiCall())
                } catch (exception: Exception) {
                    if (exception is HttpException) {
                        mapHttpExceptionToResultError(
                            exception.response()?.errorBody(),
                            exception.code(),
                            exception.message()
                        )
                    } else {
                        mapGenericExceptionToResultError(exception)
                    }
                }
            )
        }.single()
    }
}

fun <T> mapResponse(response: T): Result<T, ResultError.NetworkError> {
    return if (response !is Response<*>) {
        Result.Success(response)
    } else {
        if (response.isSuccessful) {
            Result.Success(response)
        } else {
            mapHttpExceptionToResultError(
                response.errorBody(),
                response.code(),
                response.message()
            )
        }
    }
}

fun mapHttpExceptionToResultError(
    errorBody: ResponseBody?,
    code: Int,
    message: String
) = Result.Error(
    ResultError.NetworkError(
        httpCode = code,
        httpMessage = message,
    )
)

private fun mapGenericExceptionToResultError(
    exception: Exception,
) = Result.Error(
    ResultError.NetworkError(
        exceptionTitle = exception::class.simpleName,
        exceptionMessage = exception.message,
        isConnectionError = exception.isConnectionException()
    )
)

internal fun Throwable.isConnectionException(): Boolean =
    this is ConnectException || this is UnknownHostException || this is ConnectionShutdownException