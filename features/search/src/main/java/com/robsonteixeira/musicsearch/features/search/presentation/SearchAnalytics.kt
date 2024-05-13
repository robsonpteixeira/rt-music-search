package com.robsonteixeira.musicsearch.features.search.presentation

import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent

object SearchAnalytics {
    private const val SCREEN_NAME = "search_screen"

    internal fun getScreenView() = getAnalyticsEvent("screen_view")

    internal fun getLoading() = getAnalyticsEvent("loading")

    internal fun getLoaded() = getAnalyticsEvent("loaded")

    internal fun getEmpty() = getAnalyticsEvent("empty")

    internal fun getError() = getAnalyticsEvent("error")

    internal fun getItemClick(id: String) = getAnalyticsEvent("item_click:$id")

    private fun getAnalyticsEvent(event: String) = AnalyticsEvent(SCREEN_NAME, event)
}
