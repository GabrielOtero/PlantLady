package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.dto.PlantDto
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PlantRepository {

//    suspend fun getPlantById(id: Int): Resource<Plant>
//    suspend fun getPlantsByRoom(idRoom: Int): Resource<List<Plant>>
    suspend fun getPlantsByType(idType: Int): Flow<Response<List<PlantDto>>>

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
