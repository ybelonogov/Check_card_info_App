package com.example.testnewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.testnewapp.ui.screens.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetNavController ()
        }
    }
}


@Composable
fun SetNavController (){
    val navController= rememberNavController()
    NavGraph(navHostController = navController)
}
