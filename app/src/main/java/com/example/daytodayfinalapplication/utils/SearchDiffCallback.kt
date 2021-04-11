package com.example.daytodayfinalapplication.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.daytodayfinalapplication.domain.entity.SearchResult

class SearchDiffCallback : DiffUtil.ItemCallback<SearchResult>() {

    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem?.pageId == newItem?.pageId//To change body of created functions use File | Settings | File Templates.
    }

    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean {
        return oldItem == newItem //To change body of created functions use File | Settings | File Templates.
    }

}
