package com.pankti.democompose.domain.usecase

import com.pankti.jetpackcomposedemo.democompose.domain.entries.album.AlbumResponse
import com.pankti.democompose.domain.repositories.AlbumRepository
import javax.inject.Inject


class AlbumUseCase @Inject constructor(var repo: AlbumRepository) {

    suspend fun getAlbumList(): List<AlbumResponse> = repo.getAlbumList()
}