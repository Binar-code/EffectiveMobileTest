package com.example.domain.model

import java.time.LocalDate

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)
