package com.pankti.jetpackcomposedemo.democompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.pankti.jetpackcomposedemo.democompose.ui.common.AppBar
import com.pankti.jetpackcomposedemo.democompose.ui.theme.DemoComposeTheme
import com.pankti.jetpackcomposedemo.R

@Composable
fun ProfileDashboard(navController: NavController) {
    DemoComposeTheme {

        Scaffold(
            topBar = { AppBar("Profile", true) { navController.popBackStack() } },
            content = {padding-> ShowProfile() }
        )
    }
}

@Composable
fun ShowProfile() {
    Card(modifier = Modifier.padding(16.dp), elevation = 4.dp) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Spacer(modifier = Modifier.height(30.dp))
            ProfileSection()
        }
    }
}

@Composable
fun ProfileSection() {

    val showPortfolio = remember { mutableStateOf(false) }

    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            ProfileImage(Modifier
                .padding(start = 16.dp)
                .height(96.dp)
                .width(96.dp))
            Column() {
                ProfileInfoSection(Modifier.padding(horizontal = 24.dp, vertical = 12.dp), "Pankti Prajapati")
                Button(modifier = Modifier.padding(horizontal = 24.dp), onClick = {
                    showPortfolio.value = !showPortfolio.value
                }) {
                    Text(text = "Portfolio")
                }
            }
        }
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(horizontal = 0.dp, vertical = 8.dp))
    }

    if (showPortfolio.value) {
        PortfolioContent()
    }
}

@Composable
fun PortfolioContent() {
    Surface(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .fillMaxHeight(),
        shape = RoundedCornerShape(corner = CornerSize(4.dp)),
        border = BorderStroke(width = 0.5.dp, color = Color.Blue)) {
        ProfileList(listOf<String>("Hema", "Shushma", "Jaya", "Rekha", "Vijya", "Sabki pasand Nirma"))
    }

}

@Composable
fun ProfileList(data: List<String>) {
    LazyColumn {
        items(data) { item -> ProfileRow(item) }
    }
}

@Composable
fun ProfileRow(item: String) {
    Card(modifier = Modifier
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)) {

        Row(modifier = Modifier.padding(12.dp)) {
            ProfileImage(Modifier
                .padding(8.dp)
                .height(35.dp)
                .width(35.dp))
            ProfileInfoSection(modifier = Modifier.padding(start = 8.dp), item)
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier = Modifier) {
    Card(border = BorderStroke(1.dp, color = Color.Blue), shape = CircleShape, elevation = 5.dp, modifier = modifier) {
        Image(painter = painterResource(id = R.drawable.user_placeholder),
            contentDescription = "Profile image",
            contentScale = ContentScale.Fit)
    }
}

@Composable
fun ProfileInfoSection(modifier: Modifier, name: String) {
    Column(horizontalAlignment = Alignment.Start, modifier = modifier) {
        Text(text = name, style = TextStyle(color = Color.Blue, fontFamily = FontFamily.SansSerif, fontSize = 20.sp))
        Text(text = "Android app developer",
            style = TextStyle(color = Color.Black, fontFamily = FontFamily.SansSerif, fontSize = 12.sp),
            modifier = Modifier.padding(top = 5.dp))
        Text(text = "@PSP111913",
            style = TextStyle(color = Color.Black, fontFamily = FontFamily.SansSerif, fontSize = 10.sp),
            modifier = Modifier.padding(top = 5.dp))
    }
}

