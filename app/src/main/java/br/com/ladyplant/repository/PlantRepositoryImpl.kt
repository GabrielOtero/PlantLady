package br.com.ladyplant.repository

import br.com.ladyplant.model.Plant
import br.com.ladyplant.repository.mapper.PlantMapper

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
}