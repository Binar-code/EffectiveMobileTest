package com.example.effectivemobiletest.ui.home.feed

import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import eightbitlab.com.blurview.BlurView

private const val BLUR_RADIUS = 4f
private const val OVERLAY_COLOR = 0x1A24252A

private fun setupBlur(blurView: BlurView) {
    val root =
        blurView.rootView.findViewById<View>(android.R.id.content) as? ViewGroup
            ?: (blurView.rootView as ViewGroup)

    blurView
        .setupWith(root)
        .setBlurRadius(BLUR_RADIUS)
        .setOverlayColor(OVERLAY_COLOR)
        .setBlurAutoUpdate(true)
    blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
    blurView.clipToOutline = true
}

fun courseDelegate(onFavClick: (CourseUi) -> Unit) =
    adapterDelegateViewBinding<CourseUi, FeedItem, ItemCourseBinding>(
        { inflater, parent -> ItemCourseBinding.inflate(inflater, parent, false) },
    ) {
        bind { payloads ->
            if (PAYLOAD_FAV in payloads) {
                binding.favoriteButton.setImageResource(
                    if (item.isFavorite) R.drawable.ic_fav_active else R.drawable.ic_fav_inactive,
                )
            }

            setupBlur(binding.favBlur)
            setupBlur(binding.rateBlur)
            setupBlur(binding.dateBlur)

            binding.rating.text = item.rating
            binding.date.text = item.date
            binding.title.text = item.title
            binding.description.text = item.description
            binding.price.text = item.price

            binding.favoriteButton.setImageResource(
                if (item.isFavorite) R.drawable.ic_fav_active else R.drawable.ic_fav_inactive,
            )
            binding.favoriteButton.setOnClickListener { onFavClick(item) }
        }
    }
