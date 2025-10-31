package com.example.data.di

import com.example.data.common.repository.ItemRepositoryImpl
import com.example.data.local.repository.LocalRepository
import com.example.data.network.repository.NetworkRepository
import com.example.domain.repository.ItemRepository
import com.example.domain.usecase.ObserveUseCase
import com.example.domain.usecase.RefreshUseCase
import org.koin.dsl.module

val RepositoryModule = module {
    single<LocalRepository> {
        LocalRepository(
            get()
        )
    }

    single<NetworkRepository> {
        NetworkRepository(
            get()
        )
    }

    single<ItemRepository> {
        ItemRepositoryImpl(
            get(),
            get()
        )
    }

    factory<ObserveUseCase> {
        ObserveUseCase(get())
    }

    factory<RefreshUseCase> {
        RefreshUseCase(get())
    }
}
