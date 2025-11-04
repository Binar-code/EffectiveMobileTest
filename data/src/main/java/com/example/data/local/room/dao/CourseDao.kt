package com.example.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.local.room.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    fun getCourses(): Flow<List<CourseEntity>>

    @Upsert
    suspend fun upsertCourses(data: List<CourseEntity>)

    @Query("UPDATE course SET has_like = :isFavorite WHERE public_id = :publicId")
    suspend fun updateFavorite(publicId: Int, isFavorite: Boolean)
}
