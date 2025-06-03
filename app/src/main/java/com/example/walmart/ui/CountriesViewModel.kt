package com.example.walmart.ui

import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.model.AppUiState
import com.example.walmart.model.AppUiStateModel
import com.example.walmart.model.Country
import com.example.walmart.model.RetrofitInstance
import kotlinx.coroutines.launch

class CountriesViewModel: ViewModel() {
    val data = mutableStateOf<AppUiState>(AppUiState())

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getCountries()
                data.value = AppUiState(AppUiStateModel.Success(response.body()?: listOf()))
            } catch(e: Exception) {
                data.value = AppUiState(AppUiStateModel.Error(e.message?:""))
            }
        }
    }
}