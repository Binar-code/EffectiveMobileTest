package com.example.effectivemobiletest.ui.home.feed

const val PAYLOAD_FAV = "fav"

sealed interface FeedItem { val id: Int }

data class CourseUi(
    override val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val date: String,
    val isFavorite: Boolean
): FeedItem

