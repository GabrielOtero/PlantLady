package br.com.ladyplant.navigation.navTypes

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.navigation.NavType
import br.com.ladyplant.domain.model.Plant
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Keep
@Serializable
@Parcelize
data class PlantList(
    var value: List<Plant>
) : Parcelable

val PlantListNavType = object : NavType<PlantList>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: PlantList) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): PlantList {
        return bundle.getParcelable<PlantList>(key)!!
    }

    override fun parseValue(value: String): PlantList {
        return Json.decodeFromString<PlantList>(value)
    }

    // Only required when using Navigation 2.4.0-alpha07 and lower
    override val name = "SearchParameters"
}