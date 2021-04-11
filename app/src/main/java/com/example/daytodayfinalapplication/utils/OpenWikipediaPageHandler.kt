package com.example.daytodayfinalapplication.utils

import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat.getColor
import androidx.core.net.toUri
import com.example.daytodayfinalapplication.R
import com.example.daytodayfinalapplication.domain.entity.SearchResult
import javax.inject.Inject

class OpenWikipediaPageHandler @Inject constructor(
    private val context: Context
) : ItemHandler {

    override operator fun invoke(searchResult: SearchResult) {
        val primary = getColor(context, R.color.colorPrimary)
        val uri = "https://en.m.wikipedia.org/?curid=${searchResult.pageId}".toUri()

        val customTabs: CustomTabsIntent = CustomTabsIntent.Builder()
            .setToolbarColor(primary)
            .addDefaultShareMenuItem()
            .enableUrlBarHiding()
            .build()
        customTabs.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        customTabs.launchUrl(context, uri) //To change body of created functions use File | Settings | File Templates.
    }

}
