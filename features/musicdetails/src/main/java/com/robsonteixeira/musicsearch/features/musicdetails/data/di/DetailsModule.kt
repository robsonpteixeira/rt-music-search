package com.robsonteixeira.musicsearch.features.musicdetails.data.di

import com.robsonteixeira.musicsearch.core.network.di.OpenwhydRetrofit
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.DetailsRepository
import com.robsonteixeira.musicsearch.features.musicdetails.data.api.DetailsApi
import com.robsonteixeira.musicsearch.features.musicdetails.data.repository.DetailsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DetailsModule {

    @Binds
    fun bindDetailsRepository(detailsRepository: DetailsRepositoryImpl): DetailsRepository

    companion object {
        @Singleton
        @Provides
        fun provideDetailsAPI(
            @OpenwhydRetrofit retrofit: Retrofit
        ): DetailsApi {
            return retrofit.create(DetailsApi::class.java)
        }
    }
}
