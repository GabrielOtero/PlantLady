package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.PlantLadyApi
import br.com.ladyplant.repository.dto.PlantDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(private val api: PlantLadyApi) : PlantRepository {
    override suspend fun getPlantsByType(idType: Int): Flow<Response<List<PlantDto>>> {
        return flow {
            emit(api.getPlantsByType(idType))
        }
    }
}