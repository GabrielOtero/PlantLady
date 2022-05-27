package br.com.ladyplant.domain.model

data class Plant(
    var id: Int?,
    var name: String?,
    var scientificName: String?,
    var origin: String?,
    var poisonous: String?,
    var light: String?,
    var water: String?,
    var overview: String?,
    var image: String?
)
