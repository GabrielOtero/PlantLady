package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.PlantLadyResponse
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.utils.Result
import br.com.ladyplant.utils.ResultError
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GetPlantsByType {
    suspend operator fun invoke(idType: Int): Result<List<Plant>, ResultError>
}