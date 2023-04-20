package br.com.ladyplant.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DomainErrorResult (
    val code: Int = GENERIC_ERROR,
    val message: String,
    val title: String? = null,
    val cause: Throwable? = null
): Parcelable {
    override fun toString(): String =
        "ErrorResult [code: $code, title: $title, message: $message, cause: $cause]"

    companion object {
        const val GENERIC_ERROR = 0
        const val UNKNOWN_ERROR = -1
    }
}
