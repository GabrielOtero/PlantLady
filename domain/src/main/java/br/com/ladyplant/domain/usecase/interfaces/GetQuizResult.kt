package br.com.ladyplant.domain.usecase.interfaces

import br.com.ladyplant.domain.model.DomainResult
import br.com.ladyplant.domain.model.Plant

interface GetQuizResult {
    suspend operator fun invoke(
        idClimate: Int,
        idGardenCare: Int,
        idAppearance: Int,
        idLight: Int,
        idInplace: Int,
        idPurpose: Int,
        idEatable: Int
    ): DomainResult<List<Plant>>
}