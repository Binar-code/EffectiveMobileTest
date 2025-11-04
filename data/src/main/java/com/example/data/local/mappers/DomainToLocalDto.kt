package com.example.data.local.mappers

import com.example.data.local.dto.LocalCourseDto
import com.example.domain.model.Course

fun Course.toDto(): LocalCourseDto = LocalCourseDto(
    id = 0,
    publicId = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate.toString(),
    hasLike = hasLike,
    publishDate = publishDate.toString()
)
