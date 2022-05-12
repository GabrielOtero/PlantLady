package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.plant.PlantRepository
import javax.inject.Inject

class GetPlantsByTypeUseCase @Inject constructor(
    private val repository: PlantRepository,
    private val mapper: PlantDtoToPlantMapper
) :
    GetPlantsByType {
    override suspend fun invoke(
        idType: Int,
        onSuccessCallback: (List<Plant>) -> Unit,
        onErrorCallback: (String) -> Unit,
        onFinishCallback: (List<Plant>?) -> Unit
    ) {
        repository.getPlantsByType(idType)
            .mapSuccess {
                mapper.mapFrom(it)
            }
            .handleResult(
                onSuccess = onSuccessCallback,
                onError = {
                    onErrorCallback(it.exceptionMessage.orEmpty())
                },
                onFinish = onFinishCallback
            )
    }
}
