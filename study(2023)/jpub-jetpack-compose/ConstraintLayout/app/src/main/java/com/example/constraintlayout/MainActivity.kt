package com.example.constraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.constraintlayout.ui.theme.ConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintLayoutTheme {
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
fun MyButton(text: String, modifier: Modifier = Modifier) {
    Button(
        onClick = { },
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun MainScreen() {
    ConstraintLayout(modifier = Modifier.size(width = 200.dp, height = 200.dp)) {
        val constraints = myConstraintSet(margin = 8.dp)

        MyButton(text = "Button1", modifier = Modifier
            .size(200.dp)
            .layoutId(constraints))
    }
}

private fun myConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button1 = createRefFor("button1")

        constrain(button1) {
            linkTo(parent.top, parent.bottom, topMargin = margin, bottomMargin = margin)
            linkTo(parent.start, parent.end, startMargin = margin, endMargin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintLayoutTheme {
        MainScreen()
    }
}