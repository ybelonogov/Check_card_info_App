package com.example.testnewapp.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testnewapp.data.repository.BinRepository
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.zIndex
import com.example.testnewapp.common.history
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, binFromHistory: String = "") {
    val repository by remember {
        mutableStateOf(BinRepository())
    }
    val binNumber = remember {
        mutableStateOf(binFromHistory)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 80.dp)
        ) {


            InputBin(binNumber = binNumber)
            Button(
                onClick = {
                    CardInfo2(repository, binNumber = binNumber)
                    history.add(binNumber.value)
                },
                modifier = Modifier.fillMaxWidth(0.8F)
            ) {
                Text(text = "Check", fontSize = 16.sp)
            }
            Button(
                onClick = { navController.navigate("history_screen") },
                modifier = Modifier.fillMaxWidth(0.8F)
            ) {
                Text(text = "History", fontSize = 16.sp)
            }
            CardInfo(repository = repository, binNumber = binNumber)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBin(binNumber: MutableState<String>) {
    TextField(
        value = binNumber.value,
        onValueChange = { bin: String ->
            if (bin.length <= 8) {
                binNumber.value = bin

            }
        },
        label = {
            Text(
                text = "BIN Number (8 digits)",
                maxLines = 1,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier
            .fillMaxWidth(0.8F)
            .padding(20.dp),


        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
    )
}


fun CardInfo2(repository: BinRepository, binNumber: MutableState<String>) {

    val coroutineScope = CoroutineScope(Dispatchers.Default)
    coroutineScope.launch {
        repository.getInfo(binNumber.value)
    }
}


@Composable
fun CardInfo(repository: BinRepository, binNumber: MutableState<String>) {

    LaunchedEffect(true) {
        repository.getInfo(binNumber.value)
    }
    val cardInfo by repository.cardInfo.collectAsState()
    Surface(
        modifier = Modifier
            .zIndex(10f)
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(20.dp)),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            cardInfo?.let { info ->
                Text(text = "Bank: ${info.bank.name}")
                Text(text = "Site: ${info.bank.url}")
//                Text(text = "Brand: ${info.brand}")
                Text(text = "Country: ${info.country.name}  ${info.country.emoji}")
//                Text(text = "Number: ${info.number}")
                Text(text = "Prepaid: ${info.prepaid}")
                Text(text = "Scheme: ${info.scheme}")
                Text(text = "Type: ${info.type}")
            } ?: Text(text = "Card Info is null")
        }
    }
}