package com.pankti.jetpackcomposedemo.democompose.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pankti.jetpackcomposedemo.R

@Composable
fun AppBar(title: String = "", backIconAvailable: Boolean = false, onBackPressed: () -> Unit = {}) {
    TopAppBar(backgroundColor = Color.Blue, contentColor = Color.White, elevation = 2.dp) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (backIconAvailable) {
                IconButton(onClick = { onBackPressed() }, modifier = Modifier.size(40.dp)) {
                    Icon(imageVector = ImageVector.vectorResource(id = R.drawable.back), contentDescription = "Back")
                }
            }
            Text(text = title, modifier = Modifier.padding(start = 12.dp), style = TextStyle(fontWeight = FontWeight.Bold,
                fontSize = 18.sp),)
        }
    }
}