package com.example.walmart.model

import retrofit2.Response
import retrofit2.http.GET

interface CountryAPI {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<Country>>
}