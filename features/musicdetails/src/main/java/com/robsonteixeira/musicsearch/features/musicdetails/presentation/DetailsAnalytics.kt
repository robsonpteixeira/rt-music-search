package com.robsonteixeira.musicsearch.features.musicdetails.presentation

import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent

object DetailsAnalytics {
    private const val SCREEN_NAME = "details_screen"

    internal fun getScreenView() = getAnalyticsEvent("screen_view")

    internal fun getLoading() = getAnalyticsEvent("loading")

    internal fun getLoaded() = getAnalyticsEvent("loaded")

    internal fun getError() = getAnalyticsEvent("error")

    internal fun getSrcClick(src: String) = getAnalyticsEvent("src:$src")

    private fun getAnalyticsEvent(event: String) = AnalyticsEvent(SCREEN_NAME, event)
}
