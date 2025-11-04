package com.example.domain.usecase

import com.example.domain.repository.ItemRepository

class UpdateFavoriteUseCase(val repository: ItemRepository) {
    suspend operator fun invoke(publicId: Int, isFavorite: Boolean) =
        repository.updateFavorite(publicId, isFavorite)
}
