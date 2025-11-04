package com.example.effectivemobiletest.ui.home.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager

class HomeAdapter(
    onFavClick: (CourseUi) -> Unit,
) : ListAdapter<FeedItem, RecyclerView.ViewHolder>(Diff) {

    private val delegates = AdapterDelegatesManager<List<FeedItem>>()
        .addDelegate(courseDelegate(onFavClick))

    init { setHasStableIds(true) }

    override fun getItemId(position: Int) = getItem(position).id.toLong()

    override fun getItemViewType(position: Int) =
        delegates.getItemViewType(currentList, position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        delegates.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegates.onBindViewHolder(currentList, position, holder)

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>
    ) = delegates.onBindViewHolder(currentList, position, holder, payloads)

    private object Diff : DiffUtil.ItemCallback<FeedItem>() {
        override fun areItemsTheSame(a: FeedItem, b: FeedItem) =
            a.id== b.id && a::class == b::class

        override fun areContentsTheSame(a: FeedItem, b: FeedItem) = a == b

        override fun getChangePayload(oldItem: FeedItem, newItem: FeedItem): Any? =
            if (oldItem is CourseUi && newItem is CourseUi &&
                oldItem.isFavorite != newItem.isFavorite
            ) PAYLOAD_FAV else null
    }
}
