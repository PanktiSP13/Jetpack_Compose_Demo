package com.pankti.democompose.di

import com.pankti.jetpackcomposedemo.democompose.data.network.KtorClient
import com.pankti.jetpackcomposedemo.democompose.data.network.UserProfileApi
import com.pankti.democompose.data.reposityimpl.UserProfileRepositoryImpl
import com.pankti.democompose.domain.repositories.UserProfileRepository
import com.pankti.democompose.domain.usecase.UserProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object UserProfileModule {

    @Provides
    @ViewModelScoped
    fun providesUserProfileApi(): UserProfileApi = UserProfileApi(KtorClient.client)


    @Provides
    @ViewModelScoped
    fun providesUserProfileRepository(api : UserProfileApi): UserProfileRepository = UserProfileRepositoryImpl(api)


    @Provides
    @ViewModelScoped
    fun providesUserProfileUseCase(repository: UserProfileRepository): UserProfileUseCase = UserProfileUseCase(repository)

}