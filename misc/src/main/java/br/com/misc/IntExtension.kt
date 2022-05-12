package br.com.misc

import kotlin.random.Random

fun Int.Companion.random(from: Int = MIN_VALUE, until: Int = MAX_VALUE) =
    Random.nextInt(from, until)