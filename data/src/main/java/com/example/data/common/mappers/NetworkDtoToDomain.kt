package com.example.data.common.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.network.dto.NetworkCourseDto
import com.example.domain.model.Course

@RequiresApi(Build.VERSION_CODES.O)
fun NetworkCourseDto.toDomain() =
    Course(
        id = id,
        title = title,
        text = text,
        price = price,
        rate = rate.toFloat(),
        startDate = startDate.toLocalDate(),
        hasLike = hasLike,
        publishDate = publishDate.toLocalDate(),
    )
