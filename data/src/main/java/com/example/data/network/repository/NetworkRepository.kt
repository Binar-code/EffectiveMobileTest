package com.example.data.network.repository

import android.util.Log
import com.example.data.network.dto.NetworkCourseDto
import com.example.data.network.service.ApiService
import retrofit2.HttpException
import java.util.concurrent.CancellationException
import com.example.domain.result.Result

class NetworkRepository(
    private val api: ApiService
) {
    suspend fun getCourses(): Result<List<NetworkCourseDto>> {
        try {
            val response = api.getCourses()
            return Result.Success(response.courses)
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            return Result.Error.HttpError(
                code = e.code(),
                msg = e.message()
            )
        } catch (e: Exception) {
            return Result.Error.Unknown
        }
    }
}