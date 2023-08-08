package com.example.testnewapp.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.testnewapp.common.history
import com.example.testnewapp.ui.theme.Typography


@Composable
fun HistoryScreen(navController: NavHostController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Text(
            text = "history",
            textAlign = TextAlign.Center, style = Typography.headlineLarge
        )
        LazyColumn(
            modifier = Modifier.padding(vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(history) { item ->
                if (item != "")
                    Button(
                        onClick = {

                            navController.navigate("main_screen")
                        },
                        modifier = Modifier.fillMaxWidth(0.8F)
                    ) {
                        Text(text = item, fontSize = 16.sp)
                    }
            }

        }
    }

}