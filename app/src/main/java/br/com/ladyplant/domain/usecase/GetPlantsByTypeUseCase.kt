package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.utils.Result
import br.com.ladyplant.utils.ResultError
import javax.inject.Inject

class GetPlantsByTypeUseCase @Inject constructor(private val repository: PlantRepository) :
    GetPlantsByType {
    override suspend fun invoke(idType: Int): Result<List<Plant>, ResultError> {
        return repository.getPlantsByType(idType)
            .mapSuccess {
                plantDtoToPlant(it)
            }
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
