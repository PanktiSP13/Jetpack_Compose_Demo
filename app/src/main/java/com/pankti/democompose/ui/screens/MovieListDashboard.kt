package com.pankti.democompose.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.pankti.democompose.ui.common.AppBar

class Movie(var movieName: String = "", var movieImage: String = "",
            var movieDescription: String = "It is a long established fact that a reader will be distracted by the readable " +
                    "content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less " +
                    "normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English.")

var movieList = listOf(Movie(movieName = "Avatar:Special Edition", movieImage = "https://tinyurl" +
        ".com/6ufbfktp"),
    Movie(movieName = "Inception" , movieImage = "https://tinyurl.com/2kusys8y"),
    Movie(movieName = "Everything Everywhere All At Once", movieImage = "https://tinyurl.com/yxkzx8x5"),
    Movie(movieName = "No Time To Die", movieImage = "https://tinyurl.com/3be9kp7v"),
    Movie(movieName = "Shang-Chi And The Legend Of The Ten Rings", movieImage = "https://tinyurl.com/yc3zm2x9"))

@Composable
fun MovieDashboard(navController: NavController){
    Scaffold(
        topBar = { AppBar("Movies", true) { navController.popBackStack() } },
        content = {padding -> ShowMovieList() })

}


@Composable
fun ShowMovieList(){
    Column {
        LazyColumn {
            items(movieList) {
                MovieRow(movie = it){
                    Log.e("TAG", "Movie : " +it.movieName + " Clicked")
                }
            }
        }
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieRow(movie: Movie, callBack : () -> Unit = {}) {

    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { callBack() }
        .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp), ) {

        Row(modifier = Modifier.padding(12.dp)) {
            GlideImage(model = movie.movieImage,
                contentDescription = "MovieImage",
                modifier = Modifier.size(width = 100.dp, height = 150.dp))
            Column(modifier = Modifier.padding(12.dp)) {
                Text(text = movie.movieName,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
                Text(text = movie.movieDescription, maxLines = 5,overflow = TextOverflow.Ellipsis, modifier = Modifier.padding
                    (horizontal = 8.dp))
            }
        }
    }

}