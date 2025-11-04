package com.example.data.network.repository

import com.example.data.network.dto.NetworkCourseDto
import com.example.data.network.service.ApiService
import com.example.domain.result.Result
import retrofit2.HttpException
import java.util.concurrent.CancellationException

@Suppress("TooGenericExceptionCaught")
class NetworkRepository(
    private val api: ApiService,
) {
    suspend fun getCourses(): Result<List<NetworkCourseDto>> {
        val result =
            try {
                val response = api.getCourses()
                Result.Success(response.courses)
            } catch (e: CancellationException) {
                throw e
            } catch (e: HttpException) {
                Result.Error.HttpError(e.code(), e.message())
            } catch (e: Exception) {
                Result.Error.Unknown(e)
            }

        return result
    }
}
