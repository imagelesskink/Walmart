package com.example.walmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import com.example.walmart.model.AppUiState
import com.example.walmart.model.AppUiStateModel
import com.example.walmart.model.Country
import com.example.walmart.ui.CountriesViewModel
import com.example.walmart.ui.theme.WalmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = CountriesViewModel()
        setContent {
            WalmartTheme {
                // A surface container using the 'background' color from the theme
                val state = viewModel.data.value
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen(modifier = Modifier.fillMaxSize(), uiState = state)
                }
            }
        }
    }
}

@Composable
fun Screen(modifier: Modifier = Modifier, uiState: AppUiState = AppUiState()) {
    Box(modifier = modifier) {
        when (val state = uiState.state) {
            is AppUiStateModel.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is AppUiStateModel.Error -> Text(text = "Failed to connect to server: " + state.message)
            is AppUiStateModel.Success -> {
                Countries(countries = state.data,
                modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Countries(countries: List<Country>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(countries) { country ->
            Row() {
                Text(text = "${country.name}, ${country.region}")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = country.code)
            }
            Text(text = country.capital)
            HorizontalDivider()
        }
    }
}