package br.com.ladyplant.repository.utils

interface StubApi {
    suspend fun stubRequest(idType: Int): List<Any>
}
