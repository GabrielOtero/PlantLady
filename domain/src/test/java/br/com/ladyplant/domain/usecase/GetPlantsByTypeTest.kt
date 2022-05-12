package br.com.ladyplant.domain.usecase

import br.com.ladyplant.domain.mapper.PlantDtoToPlantMapper
import br.com.ladyplant.domain.usecase.GetPlantsByTypeUseCase
import br.com.ladyplant.repository.dto.PlantDto
import br.com.ladyplant.repository.plant.PlantRepository
import br.com.ladyplant.repository.utils.Result
import br.com.ladyplant.repository.utils.ResultError
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPlantsByTypeTest {

    private lateinit var repository: PlantRepository
    private lateinit var useCase: GetPlantsByTypeUseCase

    private fun init(result: Result<List<PlantDto>, ResultError>) {
        repository = mockk()

        coEvery { repository.getPlantsByType(any()) } returns result
        useCase = GetPlantsByTypeUseCase(repository, PlantDtoToPlantMapper())
    }

    @Test
    fun `WHEN repository return 0 plants ASSERT useCase call onSuccess with empty list`() =
        runTest {
            launch(Dispatchers.Default) {
                init(Result.Success(listOf()))
                useCase.invoke(
                    idType = 1,
                    onSuccessCallback = {
                        assertTrue(it.isEmpty())
                    })
            }
        }

    @Test
    fun `WHEN repository throws Unavailable Network ASSERT useCase call onError with the exception message`() =
        runTest {
            launch(Dispatchers.Default) {
                init(Result.Error(ResultError.UnavailableNetworkConnectionError()))

                useCase.invoke(
                    idType = 1,
                    onErrorCallback = {
                        assertEquals(
                            ResultError.UnavailableNetworkConnectionError().exceptionMessage,
                            it
                        )
                    })
            }
        }
}