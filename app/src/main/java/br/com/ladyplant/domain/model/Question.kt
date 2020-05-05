package br.com.ladyplant.domain.model

data class Question(val title: String, val options: List<String>, var answer : Int? = null)
