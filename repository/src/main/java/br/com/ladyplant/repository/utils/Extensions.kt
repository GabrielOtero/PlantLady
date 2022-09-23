package br.com.ladyplant.repository.utils

import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException


suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T, DataErrorResult.NetworkError> {
    return try {
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
}

fun <T> mapResponse(response: T): Result<T, DataErrorResult.NetworkError> {
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
    DataErrorResult.NetworkError(
        httpCode = code,
        httpMessage = message,
        exceptionMessage = message,
    )
)

private fun mapGenericExceptionToResultError(
    exception: Exception,
) = Result.Error(
    DataErrorResult.NetworkError(
        exceptionTitle = exception::class.simpleName,
        exceptionMessage = exception.message ?: "",
        httpMessage = exception.message ?: "",
        isConnectionError = exception.isConnectionException()
    )
)

internal fun Throwable.isConnectionException(): Boolean =
    this is ConnectException || this is UnknownHostException || this is ConnectionShutdownException
