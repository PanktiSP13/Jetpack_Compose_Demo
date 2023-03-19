package com.pankti.democompose.domain.entries.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pankti.democompose.domain.usecase.AlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val albumUseCase: AlbumUseCase) : ViewModel() {

    private var _albumList = MutableStateFlow<List<AlbumResponse>>(listOf())
    var albumList: MutableStateFlow<List<AlbumResponse>> = _albumList

    init {
        getAlbumList()
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            val response = albumUseCase.getAlbumList()
            if (response.isNotEmpty()) _albumList.value = response
        }
    }


}