package com.example.daytodayfinalapplication.view.adapters

import android.graphics.Typeface.BOLD
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.daytodayfinalapplication.R
import com.example.daytodayfinalapplication.domain.entity.Page
import com.example.daytodayfinalapplication.domain.entity.SearchResult
import com.example.daytodayfinalapplication.utils.SearchDiffCallback
import com.example.daytodayfinalapplication.utils.SearchItemListener
import javax.inject.Inject

/**
 * SearchListAdapter and ViewHolder for SearchResult List View.
 */
class SearchListAdapter @Inject constructor() :
    ListAdapter<SearchResult, SearchListAdapter.SearchViewHolder>(SearchDiffCallback()) {

    var clickListener: SearchItemListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_wiki, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        if (adapterPosition == RecyclerView.NO_POSITION) return

        val item = getItem(adapterPosition)
        holder.bind(item, clickListener)
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)
        private val description = itemView.findViewById<TextView>(R.id.description)
        private val photo = itemView.findViewById<ImageView>(R.id.photo)

        fun bind(item: SearchResult, clickListener: SearchItemListener?) {
            itemView.setOnClickListener {
                itemView.context
                clickListener?.onSearchResultClicked(item)
            }

            val titleText = item.title.toSpannable()
            if (item.query.isNotBlank()) {
                val start = item.title.indexOf(item.query, ignoreCase = true)
                if (start != -1) {
                    titleText[start, start + item.query.length] = StyleSpan(BOLD)
                }
            }

            title.text = buildSpannedString {
                append(titleText)
            }

            description.text = item.description

            Glide.with(itemView.context)
                .load(item.imageUrl)
                .apply(RequestOptions.centerCropTransform())
                .into(photo)
        }

    }

}
