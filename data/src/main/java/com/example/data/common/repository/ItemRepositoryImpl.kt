package com.example.data.common.repository

import com.example.data.common.mappers.toDomain
import com.example.data.local.mappers.toDto
import com.example.data.local.mappers.toEntity
import com.example.data.local.mappers.toLocalDto
import com.example.data.local.repository.LocalRepository
import com.example.data.network.repository.NetworkRepository
import com.example.domain.model.Course
import com.example.domain.repository.ItemRepository
import com.example.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl(
    private val local: LocalRepository,
    private val network: NetworkRepository
): ItemRepository {
    override fun observe(): Flow<List<Course>> {
        val res = local.observe()
        return res.map { list -> list.map { item -> item.toLocalDto().toDomain() } }
    }

    override suspend fun refresh(): Result<List<Course>> {
        return when (val res = network.getCourses()) {
            is Result.Success -> {
                return Result.Success(res.data.map {item -> item.toDomain()})
            }

            is Result.Error -> res
        }
    }

    override suspend fun updateCache(data: List<Course>) =
        local.updateLocal(data.map { item -> item.toDto().toEntity()})

    override suspend fun updateFavorite(publicId: Int, isFavorite: Boolean) =
        local.updateFavorite(publicId, isFavorite)

}
