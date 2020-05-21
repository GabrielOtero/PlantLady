package br.com.ladyplant.data.repository

import androidx.test.espresso.idling.CountingIdlingResource
import br.com.ladyplant.data.repository.mapper.PlantMapper
import br.com.ladyplant.domain.model.Constants
import br.com.ladyplant.domain.model.Plant

class PlantRepositoryImpl(
    private val api: PlantLadyApi,
    private val responseHandler: ResponseHandler
) : PlantRepository {

    private var myIdlingResource: CountingIdlingResource =
        CountingIdlingResource(Constants.IDLE_RESOURCE_NAME)

    override suspend fun getPlantById(id: Int): Resource<Plant> {
        myIdlingResource.increment()
        return try {
            val response = api.getPlantById(id)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        } finally {
            myIdlingResource.decrement()
        }
    }

    override suspend fun getPlantsByRoom(idRoom: Int): Resource<List<Plant>> {
        myIdlingResource.increment()
        return try {
            val response = api.getPlantsByRoom(idRoom)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        } finally {
            myIdlingResource.decrement()
        }
    }

    override suspend fun getPlantsByType(idType: Int): Resource<List<Plant>> {
        myIdlingResource.increment()
        return try {
            val response = api.getPlantsByType(idType)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        } finally {
            myIdlingResource.decrement()
        }
    }

    override suspend fun getQuizResult(
        idClimate: Int,
        idGardenCare: Int,
        idAppearance: Int,
        idLight: Int,
        idInplace: Int,
        idPurpose: Int,
        idEatable: Int
    ): Resource<List<Plant>> {
        myIdlingResource.increment()
        return try {
            val response = api.getQuizResult(
                idClimate,
                idGardenCare,
                idAppearance,
                idLight,
                idInplace,
                idPurpose,
                idEatable
            )
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        } finally {
            myIdlingResource.decrement()
        }
    }

    override suspend fun getPlantsByText(text: String): Resource<List<Plant>> {
        myIdlingResource.increment()
        return try {
            val response = api.getPlantByText(text)
            return responseHandler.handleSuccess(PlantMapper().transform(response))
        } catch (e: Exception) {
            responseHandler.handleException(e)
        } finally {
            myIdlingResource.decrement()
        }
    }
}