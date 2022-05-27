package br.com.ladyplant.repository.utils

sealed class ResultError(open val exceptionMessage: String?) {
    class NetworkError(
        val httpCode: Int = -1,
        val httpMessage: String? = null,
        val serverCode: String? = null,
        val serverMessage: String? = null,
        val exceptionTitle: String? = null,
        override val exceptionMessage: String? = null,
        val localizedMessage: String? = null,
        val isConnectionError: Boolean = false,
        val expectedAction: String? = null,
    ) : ResultError(httpMessage), BBXError {

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

    class UnavailableNetworkConnectionError : ResultError("Connection unavailable")
    class UnauthorizedError : ResultError("User not authorized")
    class UnknownError(val msg: String? = null) : ResultError(msg ?: "Unknown error")
    class InvalidFieldsError(val fieldIds: List<String>) : ResultError("Invalid fields ($fieldIds)")
}

interface BBXError {
    val errorDomain: String?
    val errorDescription: String?
}

