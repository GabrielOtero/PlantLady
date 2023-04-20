package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.DataErrorResultToDomainErrorResultMapper
import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetQuizResult
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.utils.Result
import javax.inject.Inject

class GetQuizResultUseCase @Inject constructor(
    private val repository: PlantRepository,
    private val mapper: PlantDtoToPlantMapper,
    private val errorMapper: DataErrorResultToDomainErrorResultMapper
) : GetQuizResult {
    override suspend fun invoke(
        idClimate: Int,
        idGardenCare: Int,
        idAppearance: Int,
        idLight: Int,
        idInplace: Int,
        idPurpose: Int,
        idEatable: Int
    ): DomainResult<List<Plant>> {
        return when (val result = repository.getQuizResult(
            idClimate = idClimate,
            idGardenCare = idGardenCare,
            idAppearance = idAppearance,
            idLight = idLight,
            idInplace = idInplace,
            idPurpose = idPurpose,
            idEatable = idEatable
        )) {
            is Result.Success -> DomainResult.Success(mapper.mapFrom(result.value))
            is Result.Error -> DomainResult.Failure(errorMapper.mapFrom(result.value))
        }
    }
}