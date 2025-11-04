package com.example.effectivemobiletest.ui.home

import com.example.domain.model.Course

data class HomeUiState(
    val items: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val mode: ScreenMode = ScreenMode.HOME,
    val sortDesc: Boolean = false
)
