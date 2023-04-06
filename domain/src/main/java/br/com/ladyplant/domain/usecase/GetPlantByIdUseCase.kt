package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.DataErrorResultToDomainErrorResultMapper
import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantById
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.utils.Result
import javax.inject.Inject

class GetPlantByIdUseCase @Inject constructor(
    private val repository: PlantRepository,
    private val mapper: PlantDtoToPlantMapper,
    private val errorMapper: DataErrorResultToDomainErrorResultMapper
) :
    GetPlantById {
    override suspend fun invoke(
        idPlant: Int,
    ): DomainResult<Plant> {
        return when (val result = repository.getPlantById(idPlant)) {
            is Result.Success -> DomainResult.Success(mapper.mapFrom(result.value))
            is Result.Error -> DomainResult.Failure(errorMapper.mapFrom(result .value))
        }
    }
}
