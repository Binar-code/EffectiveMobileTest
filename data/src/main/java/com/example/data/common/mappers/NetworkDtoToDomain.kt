package com.example.data.common.mappers

import com.example.data.network.dto.NetworkCourseDto
import com.example.domain.model.Course

fun NetworkCourseDto.toDomain() = Course(
    id = id,
    title = title,
    text = text,
    price = price.replace(" ", "").toInt(),
    rate = rate.toFloat(),
    startDate = startDate.toLocalDate(),
    hasLike = hasLike,
    publishDate = publishDate.toLocalDate()
)
