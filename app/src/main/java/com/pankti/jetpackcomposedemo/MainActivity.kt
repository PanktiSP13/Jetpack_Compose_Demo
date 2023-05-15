package com.pankti.jetpackcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pankti.democompose.navigation.NavigationScreens
import com.pankti.democompose.ui.screens.MainDashboard
import com.pankti.democompose.ui.screens.MovieDashboard
import com.pankti.democompose.ui.screens.MovieDetailScreen
import com.pankti.democompose.ui.screens.ProfileDashboard
import com.pankti.democompose.ui.screens.TapCounter
import com.pankti.democompose.ui.screens.UIWidgetDashboard
import com.pankti.democompose.ui.screens.UserProfileRootUI
import com.pankti.democompose.ui.theme.DemoComposeTheme
import com.pankti.jetpackcomposedemo.democompose.ui.screens.NetworkCallWithKtor
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = NavigationScreens.MainDashboard.name) {

                    composable(NavigationScreens.MainDashboard.name) { MainDashboard(navController = navController) }

                    composable(NavigationScreens.TapCounter.name) { TapCounter(navController = navController) }

                    composable(NavigationScreens.ProfileDashboard.name) { ProfileDashboard(navController = navController) }

                    composable(NavigationScreens.MovieDashboard.name) { MovieDashboard(navController = navController) }

                    composable(NavigationScreens.MovieDetailScreen.name) { MovieDetailScreen(navController = navController) }

                    composable(NavigationScreens.UIWidgetsDashboard.name) { UIWidgetDashboard(navController = navController) }

                    composable(NavigationScreens.NetworkCallWithKtor.name) { NetworkCallWithKtor(navController = navController) }

                    composable(NavigationScreens.UserProfile.name) { UserProfileRootUI(navController = navController) }

                }
            }
        }
    }

}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    DemoComposeTheme { content() }
}

