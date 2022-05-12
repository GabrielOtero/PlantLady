package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.utils.safeApiCall
import br.com.ladyplant.repository.PlantLadyApi
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.utils.ResultError
import br.com.ladyplant.repository.utils.Result
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(private val api: PlantLadyApi) : PlantRepository {
    override suspend fun getPlantsByType(idType: Int): Result<List<PlantDto>, ResultError> {
        return safeApiCall {
            api.getPlantsByType(idType)
        }
    }
}