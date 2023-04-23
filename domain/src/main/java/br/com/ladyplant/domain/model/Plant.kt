package br.com.ladyplant.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Keep
@Serializable
@Parcelize
data class Plant(
    var id: Int,
    var name: String,
    var scientificName: String,
    var origin: String,
    var poisonous: String,
    var light: String,
    var water: String,
    var overview: String,
    var image: String
) : Parcelable