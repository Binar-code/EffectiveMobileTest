package com.example.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.dto.LocalCourseDto
import com.example.data.local.room.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    fun getCourses(): Flow<List<LocalCourseDto>>

    @Upsert
    suspend fun upsertCourses(data: List<CourseEntity>)
}
