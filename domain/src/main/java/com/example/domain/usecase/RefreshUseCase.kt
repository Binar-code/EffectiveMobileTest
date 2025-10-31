package com.example.domain.usecase

import com.example.domain.model.Course
import com.example.domain.repository.ItemRepository
import com.example.domain.result.Result

class RefreshUseCase(private val repo: ItemRepository) {
    suspend operator fun invoke(): Result<List<Course>> = repo.refresh()
}