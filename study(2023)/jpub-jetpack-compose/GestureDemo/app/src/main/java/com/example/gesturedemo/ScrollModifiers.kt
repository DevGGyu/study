package com.example.gesturedemo

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

@Composable
fun ScrollModifiers() {

    val image = ImageBitmap.imageResource(id = R.drawable.vacation)

    Box(modifier = Modifier
        .size(150.dp)
        .verticalScroll(rememberScrollState())
        .horizontalScroll(rememberScrollState())) {
        Canvas(
            modifier = Modifier.size(300.dp, 270.dp)
        ) {
            drawImage(
                image = image,
                topLeft = Offset(x = 0f, y = 0f)
            )
        }
    }
}