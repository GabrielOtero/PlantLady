package br.com.ladyplant.repository.plant

import br.com.ladyplant.repository.PlantLadyApi
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.utils.Result
import br.com.ladyplant.repository.utils.DataErrorResult
import br.com.misc.random
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PlantRepositoryTest {

    private lateinit var api: PlantLadyApi
    private lateinit var repository: PlantRepositoryImpl

    private fun init(result: List<PlantDto>? = null, exception: Exception? = null) {
        api = mockk()

        result?.let {
            coEvery { api.getPlantsByType(any()) } returns result
        }

        exception?.let {
            coEvery { api.getPlantsByType(any()) } throws exception
        }

        repository = PlantRepositoryImpl(api)
    }

    @Test
    fun `WHEN api returns 0 plants ASSERT repository returns success with empty list`() = runTest {
        init(listOf())
        val idType = Int.random()
        val plantsByType = repository.getPlantsByType(idType)

        coVerify(exactly = 1) { api.getPlantsByType(idType) }

        assertTrue(plantsByType is Result.Success)
        plantsByType.handleResult(
            onSuccess = {
                assertEquals(0, it.size)
            }
        )
    }

    @Test
    fun `WHEN api throws exception ASSERT repository returns error with message `() = runTest {
        val idType = Int.random()
        val exceptionMessage = "Exception Message"
        init(exception = Exception(exceptionMessage))

        val plantsByType = repository.getPlantsByType(idType)

        coVerify(exactly = 1) { api.getPlantsByType(idType) }

        assertTrue(plantsByType is Result.Error)
        plantsByType.handleResult(
            onError = {
                assertTrue(it is DataErrorResult.NetworkError)
                assertEquals(exceptionMessage, it.exceptionMessage)
            }
        )
    }
}
