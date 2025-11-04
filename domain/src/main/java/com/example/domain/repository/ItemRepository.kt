package com.example.domain.repository

import com.example.domain.model.Course
import com.example.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun observe(): Flow<List<Course>>

    suspend fun refresh(): Result<List<Course>>

    suspend fun updateCache(data: List<Course>): Unit

    suspend fun updateFavorite(publicId: Int, isFavorite: Boolean): Unit
}
