package br.com.ladyplant.domain.model

sealed class DomainResult<out T : Any> {

    abstract fun getDataOrNull(): T?

    data class Success<out T : Any>(val data: T) : DomainResult<T>() {
        override fun getDataOrNull(): T = data
    }

    data class Failure(val errorResult: DomainErrorResult) : DomainResult<Nothing>() {
        override fun getDataOrNull(): Nothing? = null
    }

    companion object {
        fun <T: Any> success(value: T): DomainResult<T> = Success(value)

        fun failure(errorResult: DomainErrorResult): DomainResult<Nothing> = Failure(errorResult)
    }
}