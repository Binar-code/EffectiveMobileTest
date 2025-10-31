package com.example.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "public_id") val publicId: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Float,
    @ColumnInfo(name = "start_date") val startDate: String,
    @ColumnInfo(name = "has_like") val hasLike: Boolean,
    @ColumnInfo(name = "publish_date") val publishDate: String
)
