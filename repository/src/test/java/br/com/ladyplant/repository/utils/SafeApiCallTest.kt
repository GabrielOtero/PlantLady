package br.com.ladyplant.repository.utils

import br.com.misc.random
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

// TODO assert all httpException type
class SafeApiCallTest {

    private lateinit var api: StubApi

    private fun init(result: List<Any>? = null, exception: Exception? = null) {
        api = mockk()

        result?.let {
            coEvery { api.stubRequest(any()) } returns result
        }

        exception?.let {
            coEvery { api.stubRequest(any()) } throws exception
        }
    }

    @Test
    fun `WHEN api returns successful ASSERT safeApiCall result is Success`() = runTest {
        init(listOf(0))
        launch(Dispatchers.Default) {
            val result = safeApiCall {
                api.stubRequest(Int.random())
            }

            assertTrue(result is Result.Success)
        }
    }

    @Test
    fun `WHEN api throws httpException ASSERT safeApiCall result is Error`() = runTest {
        init(
            exception = HttpException(
                Response.error<Any>(
                    400,
                    ResponseBody.create(null, String.random())
                )
            )
        )

        val result = safeApiCall {
            api.stubRequest(Int.random())
        }

        assertTrue(result is Result.Error)

        assertFalse((result as Result.Error).value.isConnectionError)
        assertEquals(400, result.value.httpCode)
    }

    @Test
    fun `WHEN api throws generic exception ASSERT safeApiCall result is Error`() = runTest {
        val exceptionMessage = String.random()
        init(
            exception = Exception(exceptionMessage)
        )

        val result = safeApiCall {
            api.stubRequest(Int.random())
        }

        assertTrue(result is Result.Error)

        assertFalse((result as Result.Error).value.isConnectionError)
        assertEquals(-1, result.value.httpCode)
        assertEquals(exceptionMessage, result.value.exceptionMessage)

    }
}
