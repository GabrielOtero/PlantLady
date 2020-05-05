package br.com.ladyplant.repository

import br.com.ladyplant.model.Plant

interface PlantRepository {
    suspend fun getPlantById(id: Int): Resource<Plant>
}