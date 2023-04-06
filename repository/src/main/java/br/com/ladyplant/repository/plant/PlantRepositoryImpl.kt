package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.PlantLadyApi
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.utils.Result
import br.com.ladyplant.repository.utils.DataErrorResult
import br.com.ladyplant.repository.utils.safeApiCall
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(private val api: PlantLadyApi) : PlantRepository {
    override suspend fun getPlantsByType(idType: Int): Result<List<PlantDto>, DataErrorResult> {
        return safeApiCall {
            api.getPlantsByType(idType)
        }
    }

    override suspend fun getPlantById(idPlant: Int): Result<PlantDto, DataErrorResult> {
        return safeApiCall {
            api.getPlantById(idPlant)
        }
    }
}
