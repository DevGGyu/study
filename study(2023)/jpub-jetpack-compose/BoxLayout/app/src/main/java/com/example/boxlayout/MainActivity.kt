package com.example.boxlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.boxlayout.ui.theme.BoxLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(modifier = Modifier.size(width = 290.dp, height = 90.dp)) {
        Text(text = "TopStart", Modifier.align(alignment = Alignment.TopStart))
        Text(text = "TopCenter", Modifier.align(alignment = Alignment.TopCenter))
        Text(text = "TopEnd", Modifier.align(alignment = Alignment.TopEnd))

        Text(text = "CenterStart", Modifier.align(alignment = Alignment.CenterStart))
        Text(text = "Center", Modifier.align(alignment = Alignment.Center))
        Text(text = "CenterEnd", Modifier.align(alignment = Alignment.CenterEnd))

        Text(text = "BottomStart", Modifier.align(alignment = Alignment.BottomStart))
        Text(text = "BottomCenter", Modifier.align(alignment = Alignment.BottomCenter))
        Text(text = "BottomEnd", Modifier.align(alignment = Alignment.BottomEnd))
    }

    Box(Modifier.size(200.dp).clip(CircleShape).background(Color.Blue))
    Box(Modifier.size(200.dp).clip(RoundedCornerShape(30.dp)).background(Color.Blue))
    Box(Modifier.size(200.dp).clip(CutCornerShape(30.dp)).background(Color.Yellow))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxLayoutTheme {
        MainScreen()
    }
}

@Composable
fun TextCell(text: String, modifier: Modifier = Modifier, fontSize: Int = 150) {
    val cellModifier = Modifier
        .padding(4.dp)
        .border(width = 5.dp, color = Color.Black)

    Surface {
        Text(
            text = text,
            modifier = cellModifier.then(modifier),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}