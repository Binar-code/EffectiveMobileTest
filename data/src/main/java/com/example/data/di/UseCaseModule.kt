package com.example.data.di

import com.example.domain.usecase.ObserveUseCase
import com.example.domain.usecase.RefreshUseCase
import com.example.domain.usecase.UpdateCacheUseCase
import com.example.domain.usecase.UpdateFavoriteUseCase
import org.koin.dsl.module

val UseCaseModule =
    module {
        factory<ObserveUseCase> {
            ObserveUseCase(get())
        }

        factory<RefreshUseCase> {
            RefreshUseCase(get())
        }

        factory<UpdateCacheUseCase> {
            UpdateCacheUseCase(get())
        }

        factory<UpdateFavoriteUseCase> {
            UpdateFavoriteUseCase(get())
        }
    }
