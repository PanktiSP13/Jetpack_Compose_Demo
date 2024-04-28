package com.pankti.jetpackcomposedemo.democompose.domain.entries.album

import kotlinx.serialization.Serializable

@Serializable
data class AlbumResponse(var albumId: Int? = null,
                                       var id: Int? = null,
                                       var thumbnailUrl: String? = null,
                                       var title: String? = null,
                                       var url: String? = null)
