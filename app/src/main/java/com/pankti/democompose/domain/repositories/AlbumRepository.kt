package com.pankti.democompose.domain.repositories

import com.pankti.democompose.domain.entries.album.AlbumResponse

interface AlbumRepository {
    suspend fun getAlbumList(): List<AlbumResponse>
}