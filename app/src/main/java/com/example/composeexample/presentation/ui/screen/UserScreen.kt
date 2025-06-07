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

@Composable
fun UserScreen(
    userViewModel: UserViewModel = koinViewModel()
) {
    val state by userViewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        userViewModel.handleIntent(UserIntent.LoadUsers)
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
                        onClick = { userViewModel.handleIntent(UserIntent.Refresh) }
                    ) {
                        Text("Retry")
                    }
                }
            }

            else -> {
                LazyColumn {
                    items(state.users) { user ->
                        UserItem(user = user)
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = user.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}