package com.example.data.local.mappers

import com.example.data.local.dto.LocalCourseDto
import com.example.data.local.room.entity.CourseEntity

fun LocalCourseDto.toEntity(): CourseEntity = CourseEntity(
    id = 0,
    publicId = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)
