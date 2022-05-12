package br.com.ladyplant.repository

sealed class PlantLadyResponse<T>(open val msg: String) {
    data class Success<T>(val data: T) : PlantLadyResponse<T>("")
    data class Error<T>(override val msg: String) : PlantLadyResponse<T>(msg)

    fun <U> mapResponseBy(plantDtoToPlant: (data: T) -> U)
            : PlantLadyResponse<U> {
        return if (this is Success) {
            return Success(plantDtoToPlant(this.data))
        } else {
            Error(this.msg)
        }
    }
}

