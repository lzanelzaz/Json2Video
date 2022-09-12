package ru.lzanelzaz.json2video.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.lzanelzaz.json2video.api.ApiService
import ru.lzanelzaz.json2video.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        apiService: ApiService
    ): Repository = Repository(apiService = apiService)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://api.json2video.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build().create(ApiService::class.java)
}