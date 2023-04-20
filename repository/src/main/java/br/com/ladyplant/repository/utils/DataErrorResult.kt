package br.com.ladyplant.repository.utils

sealed class DataErrorResult(open val exceptionMessage: String) {
    class NetworkError(
        val httpCode: Int = -1,
        val httpMessage: String,
        val serverCode: String? = null,
        val serverMessage: String? = null,
        val exceptionTitle: String? = null,
        override val exceptionMessage: String,
        val localizedMessage: String? = null,
        val isConnectionError: Boolean = false,
        val expectedAction: String? = null,
    ) : DataErrorResult(httpMessage), BBXError {

        override val errorDomain = if (httpCode != -1) {
            "RequestInvalidCode.${this.httpCode}"
        } else {
            this.exceptionTitle
        }

        override val errorDescription = if (httpCode != -1) {
            "Code: ${this.serverCode ?: httpCode} Message: ${this.serverMessage ?: httpMessage}"
        } else {
            this.exceptionMessage
        }

        val errorDescriptionWithFallback = this.errorDescription ?: this.httpMessage ?: this.exceptionMessage
    }

    class UnavailableNetworkConnectionError : DataErrorResult("Connection unavailable")
    class UnauthorizedError : DataErrorResult("User not authorized")
    class UnknownError(val msg: String? = null) : DataErrorResult(msg ?: "Unknown error")
    class InvalidFieldsError(val fieldIds: List<String>) : DataErrorResult("Invalid fields ($fieldIds)")
}

interface BBXError {
    val errorDomain: String?
    val errorDescription: String?
}

