package com.pankti.democompose.data.network

import com.pankti.democompose.data.network.NetworkCallRoutes.ALBUM_LIST
import com.pankti.democompose.domain.entries.album.AlbumResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class AlbumApi(var httpClient: HttpClient) {

    suspend fun getAlbums(): List<AlbumResponse> {
        return httpClient.get { url(ALBUM_LIST) }.body()
    }
}