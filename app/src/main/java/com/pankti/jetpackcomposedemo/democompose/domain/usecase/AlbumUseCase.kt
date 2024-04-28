package com.pankti.jetpackcomposedemo.democompose.domain.usecase

import com.pankti.jetpackcomposedemo.democompose.domain.entries.album.AlbumResponse
import com.pankti.jetpackcomposedemo.democompose.domain.repositories.AlbumRepository
import javax.inject.Inject


class AlbumUseCase @Inject constructor(private var repo: AlbumRepository) {

    suspend fun getAlbumList(): List<AlbumResponse> = repo.getAlbumList()
}