package com.example.data.local.dto

data class LocalCourseDto(
    val id: Int,
    val publicId: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
