package br.com.misc


fun String.Companion.random(minLength : Int = 2, maxLength : Int = 15): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..Int.Companion.random(minLength, maxLength))
        .map { allowedChars.random() }
        .joinToString("")
}