package br.com.ladyplant.repository.mapper

import br.com.ladyplant.model.Plant
import br.com.ladyplant.repository.dto.PlantDto

class PlantMapper : BaseMapper<PlantDto, Plant>() {
    override fun transform(entity: PlantDto): Plant {
        return Plant(
            entity.id ?: 0,
            entity.name ?: "",
            entity.scientificName ?: "",
            entity.origin ?: "",
            entity.poisonous ?: "",
            entity.light ?: "",
            entity.water ?: "",
            entity.overview ?: "",
            entity.image ?: ""
        )
    }
}
