package com.example.effectivemobiletest.ui.home.feed.mappers

import com.example.domain.model.Course
import com.example.effectivemobiletest.ui.home.feed.CourseUi

fun Course.toCourseUi(): CourseUi =
    CourseUi(
        id = id,
        title = title,
        description = text,
        price = "$price ₽",
        rating = rate.toString(),
        date = startDate.toStringDate(),
        isFavorite = hasLike,
    )
