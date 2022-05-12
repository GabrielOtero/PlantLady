package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.plant.PlantRepository
import javax.inject.Inject

class GetPlantsByTypeUseCase @Inject constructor(private val repository: PlantRepository) :
    GetPlantsByType {
    override suspend fun invoke(
        idType: Int,
        onSuccessCallback: (List<Plant>) -> Unit,
        onErrorCallback: (String) -> Unit,
        onFinishCallback: (List<Plant>?) -> Unit
    ) {
        repository.getPlantsByType(idType)
            .mapSuccess {
                plantDtoToPlant(it)
            }
            .handleResult(
                onSuccess = onSuccessCallback,
                onError = {
                    onErrorCallback(it.exceptionMessage.orEmpty())
                },
                onFinish = onFinishCallback
            )

    }

    private fun plantDtoToPlant(it: List<PlantDto>): List<Plant> {
        return it.map { dto ->
            Plant(
                id = dto.id,
                name = dto.name,
                scientificName = dto.scientificName,
                origin = dto.origin,
                poisonous = dto.poisonous,
                light = dto.light,
                water = dto.water,
                overview = dto.overview,
                image = dto.image,
            )
        }
    }

}
