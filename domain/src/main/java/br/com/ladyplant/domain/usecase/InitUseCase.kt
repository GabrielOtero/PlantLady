package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.usecase.interfaces.Init
import br.com.ladyplant.repository.plant.PlantRepository
import javax.inject.Inject

class InitUseCase @Inject constructor(
    private val repository: PlantRepository,
) : Init {
    override suspend fun invoke() {
        repository.init()
    }
}