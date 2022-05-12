package br.com.ladyplant.repository.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantDto(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = "",
    @SerializedName("cientifc_name") var scientificName: String? = "",
    @SerializedName("origin") var origin: String? = "",
    @SerializedName("poisonous") var poisonous: String? = "",
    @SerializedName("light") var light: String? = "",
    @SerializedName("water") var water: String? = "",
    @SerializedName("overview") var overview: String? = "",
    @SerializedName("image") var image: String? = ""
) : Parcelable