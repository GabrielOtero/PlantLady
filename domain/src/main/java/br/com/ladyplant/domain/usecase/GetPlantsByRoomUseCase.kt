package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.DataErrorResultToDomainErrorResultMapper
import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.domain.usecase.interfaces.GetPlantsByRoom
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.utils.Result
import javax.inject.Inject

class GetPlantsByRoomUseCase @Inject constructor(
    private val repository: PlantRepository,
    private val mapper: PlantDtoToPlantMapper,
    private val errorMapper: DataErrorResultToDomainErrorResultMapper
) : GetPlantsByRoom {
    override suspend fun invoke(
        idRoom: Int,
    ): DomainResult<List<Plant>> {
        return when (val result = repository.getPlantsByRoom(idRoom)) {
            is Result.Success -> DomainResult.Success(mapper.mapFrom(result.value))
            is Result.Error -> DomainResult.Failure(errorMapper.mapFrom(result.value))
        }
    }
}
