package br.com.ladyplant.data.repository.mapper

import br.com.ladyplant.domain.base.BaseMapper
import br.com.ladyplant.domain.model.Plant
import br.com.ladyplant.data.repository.dto.PlantDto
import br.com.ladyplant.domain.model.ItemResult

class ItemResultMapper : BaseMapper<Plant, ItemResult>() {
    override fun transform(entity: Plant): ItemResult {
        return ItemResult(entity.id, entity.name, entity.image)
    }
}
