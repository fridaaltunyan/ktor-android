package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.MainViewModel
import com.example.myapplication.data.model.UIFootballer
import com.example.myapplication.ui.FootballerIntent

@Composable
fun FootballersScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel,
) {
    val state by mainViewModel.state.collectAsState()

    LaunchedEffect(mainViewModel) {
        // Trigger the fetchMovies() when the composable is first launched.
        mainViewModel.handleIntent(FootballerIntent.LoadFootballers)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        when {
            state.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
            }

            state.error != null -> {
                Text(text = "Error: ${state.error}", color = Color.Red)
            }

            else -> {
                FootballersList(footballers = state.footballers)
            }
        }
    }

}

@Composable
fun FootballersList(footballers: List<UIFootballer>) {
    LazyColumn {
        items(footballers) { footballer ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Name: ${footballer.name}",
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Position: ${footballer.position}",
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "Club: ${footballer.clubName}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }

}