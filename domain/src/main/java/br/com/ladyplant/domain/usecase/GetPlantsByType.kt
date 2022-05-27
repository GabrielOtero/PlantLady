package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.model.Plant

interface GetPlantsByType {
    suspend operator fun invoke(
        idType: Int,
        onSuccessCallback: (List<Plant>) -> Unit = {},
        onErrorCallback: (String) -> Unit = {},
        onFinishCallback: (List<Plant>?) -> Unit = {}
    )
}
