package com.pankti.jetpackcomposedemo.democompose.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.pankti.jetpackcomposedemo.democompose.domain.entries.album.AlbumResponse
import com.pankti.democompose.domain.entries.album.AlbumViewModel
import com.pankti.jetpackcomposedemo.democompose.ui.common.AppBar
import com.pankti.democompose.ui.eventsListeners.NetworkCallWithKtorEventListener
import com.pankti.jetpackcomposedemo.democompose.ui.theme.DemoComposeTheme
import com.pankti.jetpackcomposedemo.R

@Composable
fun NetworkCallWithKtor(navController: NavController) {

    val viewModel = hiltViewModel<AlbumViewModel>()
    val albumList = viewModel.albumList.collectAsState().value

    NetworkCallWithKtorUI(albumList) { _event ->
        when (_event) {

            NetworkCallWithKtorEventListener.OnBackPressed -> navController.popBackStack()

            else -> {
                // do nothing
                Log.e("@@@", "NetworkCallWithKtor: Event not found")
            }
        }
    }
}

@Composable
fun NetworkCallWithKtorUI(albumList: List<AlbumResponse>, uiEventListener: (NetworkCallWithKtorEventListener) -> Unit = {}) {
    Scaffold(topBar = {
        AppBar(stringResource(R.string.network_call), true) {
            uiEventListener(NetworkCallWithKtorEventListener.OnBackPressed)
        }
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            AlbumList(albumList) { _event -> uiEventListener(_event) }
        }
    })
}

@Composable
fun AlbumList(albumList: List<AlbumResponse> = emptyList(), uiEventListener: (NetworkCallWithKtorEventListener) -> Unit = {}) {

    DemoComposeTheme {
        Column {
            LazyColumn {
                items(albumList) {
                    ShowAlbumRow(it)
                }
            }
        }
    }
}

@Composable
fun ShowAlbumRow(album: AlbumResponse) {

    DemoComposeTheme {
        Card(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), elevation = 4.dp, shape = RoundedCornerShape(6.dp)) {

            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {

                    AsyncImage(
                        model = album.url ?: "", contentDescription = "Image", modifier = Modifier.size(50.dp), contentScale = ContentScale.Crop)

                    Spacer(modifier = Modifier.padding(start = 12.dp))

                    Column {
                        Text(text = "Id : ${album.albumId}",
                            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 16.sp))
                        Text(text = album.title ?: "",
                            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 14.sp))
                    }

                }
            }
        }
    }
}
