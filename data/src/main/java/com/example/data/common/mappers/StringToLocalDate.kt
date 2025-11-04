package com.example.data.common.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDate(): LocalDate = LocalDate.parse(this)
