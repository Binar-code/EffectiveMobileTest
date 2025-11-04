package com.example.data.local.mappers

import com.example.data.local.dto.LocalCourseDto
import com.example.data.local.room.entity.CourseEntity

fun CourseEntity.toLocalDto() = LocalCourseDto(
    id = id,
    publicId = publicId,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)
