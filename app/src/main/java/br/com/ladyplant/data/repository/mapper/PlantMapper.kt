package br.com.ladyplant.data.repository.mapper

import br.com.ladyplant.domain.base.BaseMapper
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.data.repository.dto.PlantDto

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
            entity.image
        )
    }
}
