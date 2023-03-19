package com.pankti.democompose.di

import com.pankti.democompose.data.network.AlbumApi
import com.pankti.democompose.data.network.KtorClient
import com.pankti.democompose.data.reposityimpl.AlbumRepositoryImpl
import com.pankti.democompose.domain.repositories.AlbumRepository
import com.pankti.democompose.domain.usecase.AlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*

@Module
@InstallIn(ViewModelComponent::class)
object AlbumModule {

    @ViewModelScoped
    @Provides
    fun providesAlbumApi(): AlbumApi = AlbumApi(KtorClient.client)

    @ViewModelScoped
    @Provides
    fun providesAlbumRepository(albumApi: AlbumApi): AlbumRepository = AlbumRepositoryImpl(albumApi)

    @ViewModelScoped
    @Provides
    fun providesAlbumUseCase(repository: AlbumRepository): AlbumUseCase = AlbumUseCase(repository)
}