package br.com.ladyplant.domain.usecase.interfaces

import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant

interface GetPlantById {
    suspend operator fun invoke(idPlant: Int) : DomainResult<Plant>
}
