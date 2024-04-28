package com.pankti.jetpackcomposedemo.democompose.domain.repositories

import com.pankti.jetpackcomposedemo.democompose.domain.entries.album.AlbumResponse

interface AlbumRepository {
    suspend fun getAlbumList(): List<AlbumResponse>
}