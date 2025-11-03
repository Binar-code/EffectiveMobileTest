package com.example.effectivemobiletest.ui.home.feed

import android.R
import com.example.domain.model.Course

fun Course.toCourseUi(): CourseUi = CourseUi(
    id = id,
    title = title,
    description = text,
    price = "$price ₽",
    rating = rate.toString(),
    date = startDate.toString(),
    isFavorite = hasLike
)
