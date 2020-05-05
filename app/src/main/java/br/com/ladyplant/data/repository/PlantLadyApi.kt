package br.com.ladyplant.data.repository

import br.com.ladyplant.data.repository.dto.PlantDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantLadyApi {
    @GET("plant/{id}")
    suspend fun getPlantById(@Path("id") id: Int): PlantDto
}