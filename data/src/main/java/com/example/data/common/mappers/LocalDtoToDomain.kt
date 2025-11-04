package com.example.data.common.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.data.local.dto.LocalCourseDto
import com.example.domain.model.Course

@RequiresApi(Build.VERSION_CODES.O)
fun LocalCourseDto.toDomain(): Course =
    Course(
        id = publicId,
        title = title,
        text = text,
        price = price,
        rate = rate,
        startDate = startDate.toLocalDate(),
        hasLike = hasLike,
        publishDate = publishDate.toLocalDate(),
    )
