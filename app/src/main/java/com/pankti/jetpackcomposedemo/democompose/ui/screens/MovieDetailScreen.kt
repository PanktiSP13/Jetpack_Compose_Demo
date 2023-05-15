package com.pankti.democompose.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.pankti.jetpackcomposedemo.democompose.ui.common.AppBar

@Composable
fun MovieDetailScreen(navController: NavController) {
    Scaffold(
        topBar = { AppBar("Movie Detail", true) { navController.popBackStack() } },
        content = {padding -> MainContent(navController) })

}