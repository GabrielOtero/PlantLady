package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.utils.DataErrorResult
import br.com.ladyplant.repository.utils.Result

interface PlantRepository {

    suspend fun getPlantsByType(idType: Int): Result<List<PlantDto>, DataErrorResult>
    suspend fun getPlantsByRoom(idRoom: Int): Result<List<PlantDto>, DataErrorResult>
    suspend fun getPlantById(idPlant: Int): Result<PlantDto, DataErrorResult>

    suspend fun getQuizResult(
        idClimate: Int,
        idGardenCare: Int,
        idAppearance: Int,
        idLight: Int,
        idInplace: Int,
        idPurpose: Int,
        idEatable: Int
    ): Result<List<PlantDto>, DataErrorResult>
}
