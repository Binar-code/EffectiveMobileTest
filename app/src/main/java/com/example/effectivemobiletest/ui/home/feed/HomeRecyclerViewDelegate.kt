package com.example.effectivemobiletest.ui.home.feed

import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseDelegate(
    onFavClick: (CourseUi) -> Unit
) = adapterDelegateViewBinding<CourseUi, FeedItem, ItemCourseBinding>(
    { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) }
) {
    bind { payloads ->
        if (PAYLOAD_FAV in payloads) {
            binding.favoriteButton.setImageResource(
                if (item.isFavorite) R.drawable.ic_fav_active else R.drawable.ic_fav_inactive
            )
        }

        binding.rating.text = item.rating
        binding.date.text = item.date
        binding.title.text = item.title
        binding.description.text = item.description
        binding.price.text = item.price

        binding.favoriteButton.setImageResource(
            if (item.isFavorite) R.drawable.ic_fav_active else R.drawable.ic_fav_inactive
        )
        binding.favoriteButton.setOnClickListener { onFavClick(item) }
    }
}