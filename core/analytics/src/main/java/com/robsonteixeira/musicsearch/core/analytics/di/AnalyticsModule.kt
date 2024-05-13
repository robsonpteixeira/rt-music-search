package com.robsonteixeira.musicsearch.core.analytics.di

import com.robsonteixeira.musicsearch.core.analytics.AnalyticsEventTracker
import com.robsonteixeira.musicsearch.core.analytics.AnalyticsEventTrackerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface AnalyticsModule {

    @Binds
    fun bindAnalyticsEventTracker(analyticsEventTracker: AnalyticsEventTrackerImpl): AnalyticsEventTracker
}
