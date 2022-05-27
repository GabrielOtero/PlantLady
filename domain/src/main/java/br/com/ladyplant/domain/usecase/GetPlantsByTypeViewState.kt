package br.com.ladyplant.domain.usecase

import br.com.ladyplant.repository.dto.PlantDto

sealed class GetPlantsByTypeViewState {

    data class ShowByTypeResult(val list: List<PlantDto>?) : GetPlantsByTypeViewState()
    data class ShowError(val errorMsg: String) : GetPlantsByTypeViewState()
    object Loading : GetPlantsByTypeViewState()
}
