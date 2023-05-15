package com.pankti.democompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pankti.democompose.navigation.NavigationScreens
import com.pankti.jetpackcomposedemo.democompose.ui.common.AppBar
import com.pankti.democompose.ui.theme.DemoComposeTheme


@Composable
fun MainDashboard(navController: NavController) {
    Scaffold(
        topBar = { AppBar("Jetpack Compose",false) {} },
        content = { padding -> MainContent(navController, Modifier.padding(12.dp)) }
    )
}

@Composable
fun MainContent(navController: NavController, modifier: Modifier = Modifier) {

    DemoComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = modifier) {

                NavigateButton(title = "Tap Counter Demo") {
                    navController.navigate(route = NavigationScreens.TapCounter.name)
                }

                NavigateButton(title = "Profile Demo") {
                    navController.navigate(route = NavigationScreens.ProfileDashboard.name)
                }

                NavigateButton(title = "Movie Demo") {
                    navController.navigate(route = NavigationScreens.MovieDashboard.name)
                }

                NavigateButton(title = "UI Widgets") {
                    navController.navigate(route = NavigationScreens.UIWidgetsDashboard.name)
                }

                NavigateButton(title = "Network call using Ktor") {
                    navController.navigate(route = NavigationScreens.NetworkCallWithKtor.name)
                }

                NavigateButton(title = "User Profile") {
                    navController.navigate(route = NavigationScreens.UserProfile.name)
                }
            }
        }
    }
}

@Composable
fun NavigateButton(title: String, navigate: () -> Unit) {
    DemoComposeTheme {
        Button(onClick = { navigate() }, shape = RoundedCornerShape(12.dp)) {
            Text(text = title, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}
