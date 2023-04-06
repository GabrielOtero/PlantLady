package br.com.ladyplant.domain.mapper

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.dto.PlantDto

class PlantDtoToPlantMapper : BaseMapper<PlantDto, Plant>() {
    override fun mapFrom(from: PlantDto) =
        Plant(
            id = from.id ?: 0,
            name = from.name.orEmpty(),
            scientificName = from.scientificName.orEmpty(),
            origin = from.origin.orEmpty(),
            poisonous = from.poisonous.orEmpty(),
            light = from.light.orEmpty(),
            water = from.water.orEmpty(),
            overview = from.overview.orEmpty(),
            image = from.image.orEmpty(),
        )
}
