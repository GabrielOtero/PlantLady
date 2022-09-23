package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.DataErrorResultToDomainErrorResultMapper
import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByType
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.utils.Result
import javax.inject.Inject

class GetPlantsByTypeUseCase @Inject constructor(
    private val repository: PlantRepository,
    private val mapper: PlantDtoToPlantMapper,
    private val errorMapper: DataErrorResultToDomainErrorResultMapper
) :
    GetPlantsByType {
    override suspend fun invoke(
        idType: Int,
    ): DomainResult<List<Plant>> {
        return when (val result = repository.getPlantsByType(idType)) {
            is Result.Success -> DomainResult.success(mapper.mapFrom(result.value))
            is Result.Error -> DomainResult.Failure(errorMapper.mapFrom(result .value))
        }
    }
}
