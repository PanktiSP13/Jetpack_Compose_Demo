package com.pankti.jetpackcomposedemo.democompose.data.reposityimpl

import com.pankti.jetpackcomposedemo.democompose.data.network.AlbumApi
import com.pankti.jetpackcomposedemo.democompose.domain.entries.album.AlbumResponse
import com.pankti.jetpackcomposedemo.democompose.domain.repositories.AlbumRepository
import javax.inject.Inject


class AlbumRepositoryImpl @Inject constructor(private val api: AlbumApi) : AlbumRepository {

    override suspend fun getAlbumList(): List<AlbumResponse> = api.getAlbums()

}