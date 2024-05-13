package com.robsonteixeira.musicsearch.features.search.di

import com.robsonteixeira.musicsearch.core.network.di.OpenwhydRetrofit
import com.robsonteixeira.musicsearch.features.search.data.repository.SearchRepository
import com.robsonteixeira.musicsearch.features.search.data.api.SearchApi
import com.robsonteixeira.musicsearch.features.search.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SearchModule {

    @Binds
    fun bindSearchRepository(searchRepository: SearchRepositoryImpl): SearchRepository

    companion object {
        @Singleton
        @Provides
        fun provideSearchAPI(
            @OpenwhydRetrofit retrofit: Retrofit
        ): SearchApi {
            return retrofit.create(SearchApi::class.java)
        }
    }
}
