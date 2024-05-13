package com.robsonteixeira.musicsearch.core.analytics

import android.util.Log
import com.robsonteixeira.musicsearch.core.analytics.model.AnalyticsEvent
import javax.inject.Inject

class AnalyticsEventTrackerImpl @Inject constructor(): AnalyticsEventTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        Log.d("MusicSearchAnalytics", "Event screen: ${event.screenName}; event: ${event.event}")
    }
}
