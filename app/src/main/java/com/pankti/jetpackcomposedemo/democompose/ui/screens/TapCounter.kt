package com.pankti.democompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pankti.jetpackcomposedemo.democompose.ui.common.AppBar
import com.pankti.democompose.ui.theme.DemoComposeTheme

@Composable
fun TapCounter(navController: NavController){
    val count = remember { mutableStateOf(0) }
    Scaffold(
        topBar = { AppBar("Tap Counter",true) { navController.popBackStack() } },
        content = {padding -> ShowCircle(count.value){ newValue -> count.value = newValue } }
    )
}



@Composable
fun ShowCircle(tapCount: Int = 0, callback: (Int) -> Unit) {

    DemoComposeTheme() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Card(modifier = Modifier
                .size(100.dp)
                .clickable {
                    callback(if (tapCount > 5) tapCount - 1 else tapCount + 1)
                }, shape = CircleShape, elevation = 4.dp) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = "Tap", textAlign = TextAlign.Center)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Greeting(name = "Pankti Prajapati")
            ShowTapCounts(age = tapCount)
        }
    }
}


@Composable
fun ShowTapCounts(age: Int) {
    Text(modifier = Modifier.width(100.dp), text = age.toString(), textAlign = TextAlign.Center)
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello \n$name!", textAlign = TextAlign.Center)
}
