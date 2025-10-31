package com.example.data.local.repository

import com.example.data.local.dto.LocalCourseDto
import com.example.data.local.room.dao.CourseDao
import com.example.data.local.room.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao: CourseDao
) {
    fun observe(): Flow<List<CourseEntity>> = dao.getCourses()

    suspend fun updateLocal(data: List<CourseEntity>) = dao.upsertCourses(data)
}
