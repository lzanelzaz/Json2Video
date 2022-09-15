package ru.lzanelzaz.json2video.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.lzanelzaz.json2video.api.ApiService
import ru.lzanelzaz.json2video.bd.AppDatabase
import ru.lzanelzaz.json2video.bd.ProjectsDao
import ru.lzanelzaz.json2video.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAppRepository(
        apiService: ApiService,
        projectsDao: ProjectsDao,
        @ApplicationContext appContext: Context
    ): AppRepository =
        AppRepository(apiService = apiService, projectsDao = projectsDao, appContext = appContext)

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl("https://api.json2video.com")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        )
            .createFromAsset("database/projects.db")
            .build()

    @Provides
    @Singleton
    fun provideProjectsDao(appDatabase: AppDatabase): ProjectsDao = appDatabase.projectsDao()

}