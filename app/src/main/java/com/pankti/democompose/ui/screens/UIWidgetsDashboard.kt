package com.pankti.democompose.ui.screens


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pankti.democompose.R
import com.pankti.democompose.ui.common.AppBar


@Composable
fun UIWidgetDashboard(navController: NavController) {

    Scaffold(
        topBar = { AppBar("UI Widgets", true) { navController.popBackStack() } },
        content = {padding-> ShowUIWidgets(navController) })
}

@Composable
fun ShowUIWidgets(navController: NavController) {


    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        ToggleButton()
        CustomRadioButton()
        CustomToggleButton()
        CheckBox()
        VerticalDashedLine()
    }
}

@Composable
fun ToggleButton() {
    val checkedState = remember { mutableStateOf(true) }
    Row() {
        Switch(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        Text(text = if (checkedState.value) "On" else "Off", modifier = Modifier.padding(top = 12.dp, start = 12.dp))
    }
}


@Composable
fun CustomRadioButton() {
    val radioOptions = listOf("Apple", "Banana", "Cake")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    radioOptions.forEach { text ->
        Row(Modifier
            .fillMaxWidth()
            .selectable(selected = (text == selectedOption), onClick = {
                onOptionSelected(text)
            })
            .padding(horizontal = 16.dp)) {
            RadioButton(selected = (text == selectedOption), onClick = { onOptionSelected(text) })
            Text(text = text, style = MaterialTheme.typography.body1.merge(), modifier = Modifier.padding(start = 16.dp))
        }
    }
}

@Composable
fun CustomToggleButton() {

    val checkedState = remember { mutableStateOf(false) }
    IconToggleButton(checked = checkedState.value, onCheckedChange = { checkedState.value = !checkedState.value }) {
        Image(painter = painterResource(id = if (checkedState.value) R.drawable.selected_circle else R.drawable.unselected_circle),
            contentDescription = "custom toggle button",
            modifier = Modifier.size(20.dp))
    }
}


@Composable
fun CheckBox() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
}

@Composable
fun VerticalDashedLine() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 20f), 0f)

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Canvas(Modifier.height(100.dp)) {
            drawLine(color = Color(0XFFCCCCCC), start = Offset(0f, 0f),
                end = Offset(0f, size.height), pathEffect = pathEffect, strokeWidth = 5f)
        }
    }
}
