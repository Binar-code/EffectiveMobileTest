package com.example.effectivemobiletest.ui.home

import com.example.domain.model.Course

data class HomeUiState(
    val mode: ScreenMode = ScreenMode.HOME,
    val sortDesc: Boolean = false,
    val isLoading: Boolean = false,
    val items: List<Course> = emptyList(),
    val error: String? = null
)
