package br.com.ladyplant.data.repository

import br.com.ladyplant.data.repository.dto.PlantDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlantLadyApi {
    @GET("plant/{id}")
    suspend fun getPlantById(@Path("id") id: Int): PlantDto

    @GET("plant/room/{id}")
    suspend fun getPlantsByRoom(@Path("id") idRoom: Int): List<PlantDto>

    @GET("plant/type/{id}")
    suspend fun getPlantsByType(@Path("id") idType: Int): List<PlantDto>

    @GET("quiz")
    suspend fun getQuizResult(
        @Query("idClimate") idClimate: Int,
        @Query("idGardenCare") idGardenCare: Int,
        @Query("idAppearance") idAppearance: Int,
        @Query("idLight") idLight: Int,
        @Query("idInplace") idInplace: Int,
        @Query("idPurpose") idPurpose: Int,
        @Query("idEatable") idEatable: Int
    ): List<PlantDto>
}