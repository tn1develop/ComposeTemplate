package com.example.composeexample.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composeexample.domain.model.User
import com.example.composeexample.presentation.state.UserIntent
import com.example.composeexample.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.lazy.items
import com.example.composeexample.presentation.state.ResortsIntent
import com.example.composeexample.presentation.ui.component.ResortItem
import com.example.composeexample.presentation.viewmodel.ResortsViewModel

@Composable
fun ResortScreen(
    resortsViewModel: ResortsViewModel = koinViewModel()
) {
    val state by resortsViewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        resortsViewModel.handleIntent(ResortsIntent.LoadResorts)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Column {
                    Text(
                        text = "Error: ${state.error}",
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Button(
                        onClick = { resortsViewModel.handleIntent(ResortsIntent.Refresh) }
                    ) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                LazyColumn {
                    items(state.resorts) { resort ->
                        ResortItem(resort = resort)
                    }
                }
            }
        }
    }
}
