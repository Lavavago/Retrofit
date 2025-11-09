package com.example.retrofitexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.retrofitexample.network.RetrofitInstance
import com.example.retrofitexample.ui.theme.RetrofitExampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitExampleTheme {
                CatScreen()
            }
        }
    }
}

@Composable
fun CatScreen() {
    val scope = rememberCoroutineScope()
    var catUrl by remember { mutableStateOf<String?>(null) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text("Error: $error")
        } else if (catUrl != null) {
            AsyncImage(
                model = catUrl,
                contentDescription = "Random cat",
                modifier = Modifier
                    .size(300.dp)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            scope.launch {
                try {
                    loading = true
                    error = null
                    val cats = RetrofitInstance.api.getRandomCat()
                    catUrl = cats.firstOrNull()?.url
                } catch (e: Exception) {
                    error = e.message
                } finally {
                    loading = false
                }
            }
        }) {
            Text("🐾 Ver gato aleatorio")
        }
    }
}
