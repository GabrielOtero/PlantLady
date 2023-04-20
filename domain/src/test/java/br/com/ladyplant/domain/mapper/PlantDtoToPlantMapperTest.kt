package br.com.ladyplant.domain.mapper

import br.com.ladyplant.repository.dto.PlantDto
import org.junit.Test
import br.com.misc.random
import junit.framework.TestCase.assertEquals

class PlantDtoToPlantMapperTest {

    @Test
    fun mapperTest() {

        val dto = PlantDto(
            id = Int.random(),
            name = String.random(),
            scientificName = String.random(),
            origin = String.random(),
            poisonous = String.random(),
            light = String.random(),
            water = String.random(),
            overview = String.random(),
            image = String.random(),
        )

        val mapper = PlantDtoToPlantMapper()

        val model = mapper.mapFrom(dto)

        assertEquals(model.id, dto.id)
        assertEquals(model.name, dto.name)
        assertEquals(model.scientificName, dto.scientificName)
        assertEquals(model.origin, dto.origin)
        assertEquals(model.poisonous, dto.poisonous)
        assertEquals(model.light, dto.light)
        assertEquals(model.water, dto.water)
        assertEquals(model.overview, dto.overview)
        assertEquals(model.image, dto.image)
    }
}
