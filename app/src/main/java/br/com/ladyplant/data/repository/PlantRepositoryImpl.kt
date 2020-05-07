package br.com.ladyplant.data.repository

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.data.repository.mapper.PlantMapper

class PlantRepositoryImpl(
    private val api: PlantLadyApi,
    private val responseHandler: ResponseHandler
) : PlantRepository {
    override suspend fun getPlantById(id: Int): Resource<Plant> {
        return try {
            val response = api.getPlantById(id)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getPlantsByRoom(idRoom: Int): Resource<List<Plant>> {
        return try {
            val response = api.getPlantsByRoom(idRoom)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getPlantsByType(idType: Int): Resource<List<Plant>> {
        return try {
            val response = api.getPlantsByType(idType)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}