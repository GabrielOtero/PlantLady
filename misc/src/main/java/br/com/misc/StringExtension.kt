package br.com.misc


fun String.Companion.random(): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..Int.Companion.random(2, 15))
        .map { allowedChars.random() }
        .joinToString("")
}