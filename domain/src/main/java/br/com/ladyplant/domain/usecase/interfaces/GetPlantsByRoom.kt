package br.com.ladyplant.domain.usecase.interfaces

import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant

interface GetPlantsByRoom {
    suspend operator fun invoke(idRoom: Int) : DomainResult<List<Plant>>
}
