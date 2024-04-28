package com.pankti.jetpackcomposedemo.democompose.di

import com.pankti.jetpackcomposedemo.democompose.data.network.AlbumApi
import com.pankti.jetpackcomposedemo.democompose.data.network.KtorClient
import com.pankti.jetpackcomposedemo.democompose.data.reposityimpl.AlbumRepositoryImpl
import com.pankti.jetpackcomposedemo.democompose.domain.repositories.AlbumRepository
import com.pankti.jetpackcomposedemo.democompose.domain.usecase.AlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

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