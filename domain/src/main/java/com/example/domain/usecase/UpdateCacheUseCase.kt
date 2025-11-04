package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.ItemRepository

class UpdateCacheUseCase(private val repo: ItemRepository) {
    suspend operator fun invoke(data: List<Course>) = repo.updateCache(data)
}
