package com.example.testnewapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "main_screen"){
        composable("main_screen"){
            MainScreen(navHostController)
        }
        composable("history_screen"){
            HistoryScreen(navHostController)
        }
    }

}