package com.example.data.di

import androidx.room.Room
import com.example.data.local.room.dao.CourseDao
import com.example.data.local.room.db.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DatabaseModule = module {
    single<Database> {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, "db"
        ).build()
    }

    single<CourseDao> {
        get<Database>().courseDao()
    }
}
