package com.example.data.common.mappers

import com.example.data.local.dto.LocalCourseDto
import com.example.domain.model.Course

fun LocalCourseDto.toDomain(): Course = Course(
    id = publicId,
    title = title,
    text = text,
    price = price.toString(),
    rate = rate,
    startDate = startDate.toLocalDate(),
    hasLike = hasLike,
    publishDate = publishDate.toLocalDate()
)
