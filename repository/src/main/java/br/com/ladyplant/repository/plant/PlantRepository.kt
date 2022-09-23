package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.utils.Result
import br.com.ladyplant.repository.utils.DataErrorResult

interface PlantRepository {

    //    suspend fun getPlantById(id: Int): Resource<Plant>
//    suspend fun getPlantsByRoom(idRoom: Int): Resource<List<Plant>>
    suspend fun getPlantsByType(idType: Int): Result<List<PlantDto>, DataErrorResult>

//    suspend fun getQuizResult(
//        idClimate: Int,
//        idGardenCare: Int,
//        idAppearance: Int,
//        idLight: Int,
//        idInplace: Int,
//        idPurpose: Int,
//        idEatable: Int
//    ): Resource<List<Plant>>

//    suspend fun getPlantsByText(text: String): Resource<List<Plant>>
}
