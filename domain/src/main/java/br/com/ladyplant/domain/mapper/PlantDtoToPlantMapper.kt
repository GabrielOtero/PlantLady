package br.com.ladyplant.domain.mapper

import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.repository.dto.PlantDto

class PlantDtoToPlantMapper : BaseMapper<PlantDto, Plant>() {
    override fun mapFrom(from: PlantDto) =
        Plant(
            id = from.id,
            name = from.name,
            scientificName = from.scientificName,
            origin = from.origin,
            poisonous = from.poisonous,
            light = from.light,
            water = from.water,
            overview = from.overview,
            image = from.image,
        )
}