package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ObserveUseCase(private val repo: ItemRepository) {
    operator fun invoke(): Flow<List<Course>> = repo.observe()
}
