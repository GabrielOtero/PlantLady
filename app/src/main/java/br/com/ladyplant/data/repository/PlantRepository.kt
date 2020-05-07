package br.com.ladyplant.data.repository

import br.com.ladyplant.domain.model.Plant

interface PlantRepository {
    suspend fun getPlantById(id: Int): Resource<Plant>
    suspend fun getPlantsByRoom(idRoom: Int): Resource<List<Plant>>
    suspend fun getPlantsByType(idType: Int): Resource<List<Plant>>

    suspend fun getQuizResult(
        idClimate: Int,
        idGardenCare: Int,
        idAppearance: Int,
        idLight: Int,
        idInplace: Int,
        idPurpose: Int,
        idEatable: Int
    ): Resource<List<Plant>>

    suspend fun getPlantsByText(text: String): Resource<List<Plant>>
}