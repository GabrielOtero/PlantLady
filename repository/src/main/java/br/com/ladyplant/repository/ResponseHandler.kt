package br.com.ladyplant.repository

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler {
//    fun <T : Any> handleSuccess(data: T): PlantLadyResponse<T> {
//        return PlantLadyResponse.Success(data)
//    }
//
//    fun <T : Any> handleException(e: Exception): PlantLadyResponse<T> {
//        return when (e) {
//            is HttpException -> PlantLadyResponse.error(getErrorMessage(e.code()), null)
//            is SocketTimeoutException -> PlantLadyResponse.error(
//                getErrorMessage(ErrorCodes.SocketTimeOut.code),
//                null
//            )
//            else -> PlantLadyResponse.error(getErrorMessage(Int.MAX_VALUE), null)
//        }
//    }
//
//    private fun getErrorMessage(code: Int): String {
//        return when (code) {
//            ErrorCodes.SocketTimeOut.code -> "Timeout"
//            401 -> "Unauthorised"
//            404 -> "Not found"
//            else -> "Something went wrong"
//        }
//    }
}
