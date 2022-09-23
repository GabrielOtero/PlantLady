package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.plant.PlantRepository
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

class GetPlantsByTypeTest {

    private lateinit var repository: PlantRepository
    private lateinit var useCase: GetPlantsByTypeUseCase

    private fun init(result: Result<List<PlantDto>, DataErrorResult>) {
        repository = mockk()

        coEvery { repository.getPlantsByType(any()) } returns result
        useCase = GetPlantsByTypeUseCase(repository, PlantDtoToPlantMapper())
    }

    @Test
    fun `WHEN repository return 0 plants ASSERT useCase call onSuccess with empty list`() =
        runTest {
            init(Result.Success(listOf()))

            val idType = Int.random()
            useCase.invoke(
                idType = idType,
                onSuccessCallback = {
                    assertTrue(it.isEmpty())
                }
            )

            coVerify(exactly = 1) { repository.getPlantsByType(idType) }
        }

    @Test
    fun `WHEN repository throws Unavailable Network ASSERT useCase call onError with the exception message`() =
        runTest {
            init(Result.Error(DataErrorResult.UnavailableNetworkConnectionError()))

            val idType = Int.random()
            useCase.invoke(
                idType = idType,
                onErrorCallback = {
                    assertEquals(
                        DataErrorResult.UnavailableNetworkConnectionError().exceptionMessage,
                        it
                    )
                }
            )

            coVerify(exactly = 1) { repository.getPlantsByType(idType) }
        }
}
