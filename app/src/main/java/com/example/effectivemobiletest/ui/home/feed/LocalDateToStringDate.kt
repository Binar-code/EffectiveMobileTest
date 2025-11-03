package com.example.effectivemobiletest.ui.home.feed

import java.time.LocalDate

fun LocalDate.toStringDate(): String {
    val months = listOf(
        "Января",
        "Февраля",
        "Марта",
        "Апреля",
        "Мая",
        "Июня",
        "Июля",
        "Августа",
        "Сентября",
        "Октября",
        "Ноября",
        "Декабря"
    )

    return "${this.dayOfMonth} ${months[this.monthValue - 1]} ${this.year}"
}
