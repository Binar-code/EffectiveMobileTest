package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.ui.home.HomeViewModel
import com.example.effectivemobiletest.ui.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModule = module {
    viewModelOf(::LoginViewModel)

    viewModelOf(::HomeViewModel)
}
