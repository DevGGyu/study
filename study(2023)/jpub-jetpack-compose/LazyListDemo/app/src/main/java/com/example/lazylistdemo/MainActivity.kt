package com.example.lazylistdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.lazylistdemo.ui.theme.LazyListDemoTheme

class MainActivity : ComponentActivity() {

    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        itemArray = resources.getStringArray(R.array.car_array)

        super.onCreate(savedInstanceState)
        setContent {
            LazyListDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(itemArray = itemArray as Array<out String>)
                }
            }
        }
    }
}

@Composable
fun ImageLoader(item: String) {
    val url =
        "https://www.ebookfrenzy.com/book_examples/car_logos/${item.substringBefore(" ")}_logo.png"

    Image(
        painter = rememberImagePainter(url),
        contentDescription = "car image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(75.dp)
    )
}

@Composable
fun MainScreen(itemArray: Array<out String>) {
    ImageLoader("Plymouth GTX")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val itemArray: Array<String> = arrayOf("Cadillac Eldorado", "Ford Fairlane", "Plymouth Fury")

    LazyListDemoTheme {
        MainScreen(itemArray = itemArray)
    }
}