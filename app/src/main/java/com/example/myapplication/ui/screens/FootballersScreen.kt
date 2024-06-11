package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var footballer: UIFootballer? by remember { mutableStateOf(null) }

    LaunchedEffect(mainViewModel) {
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

            state.footballers.isNotEmpty() -> {
                FootballersList(footballers = state.footballers, footballer, mainViewModel)
            }

            else -> {
                footballer = state.singleFootballer
            }
        }
    }

}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun FootballersList(
    footballers: List<UIFootballer>,
    footballer: UIFootballer?,
    mainViewModel: MainViewModel
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            LazyColumn {
                items(footballers) { footballer ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .shadow(4.dp, RoundedCornerShape(8.dp))
                            .clickable {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail,
                                )
                            }
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
        },
        detailPane = {
            AnimatedPane {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    footballer?.let {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .shadow(4.dp, RoundedCornerShape(8.dp))
                                .clickable {
                                    mainViewModel.handleIntent(
                                        FootballerIntent.ClickOnSingleItem(
                                            footballer.name
                                        )
                                    )
                                    navigator.navigateTo(
                                        pane = ListDetailPaneScaffoldRole.Detail,
                                    )
                                }
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
        }
    )


}