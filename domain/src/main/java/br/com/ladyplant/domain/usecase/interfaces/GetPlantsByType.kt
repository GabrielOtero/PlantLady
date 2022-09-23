package br.com.ladyplant.domain.usecase.interfaces

import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant

interface GetPlantsByType {
    suspend operator fun invoke(idType: Int) : DomainResult<List<Plant>>
}
