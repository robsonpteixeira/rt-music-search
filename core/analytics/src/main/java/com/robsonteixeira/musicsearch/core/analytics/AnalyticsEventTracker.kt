package com.robsonteixeira.musicsearch.core.analytics

import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent

interface AnalyticsEventTracker {

    fun trackEvent(event: AnalyticsEvent)
}
