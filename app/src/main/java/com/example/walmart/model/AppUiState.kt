package com.example.walmart.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

@Stable
class AppUiState(state: AppUiStateModel = AppUiStateModel.Loading) {
    val state by mutableStateOf(state)
}

sealed class AppUiStateModel {
    object Loading: AppUiStateModel()
    class Error(val message: String): AppUiStateModel()
    class Success(val data: List<Country>): AppUiStateModel()
}