package com.demo.qagency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.demo.qagency.presentation.comments.components.Navigation
import com.demo.qagency.presentation.ui.theme.GhostWhite
import com.demo.qagency.presentation.ui.theme.QAgencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QAgencyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = GhostWhite,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val scaffoldState = rememberScaffoldState()
                    Navigation(navController, scaffoldState)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QAgencyTheme {
        Greeting("Android")
    }
}
