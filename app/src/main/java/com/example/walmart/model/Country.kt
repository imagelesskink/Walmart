package com.example.walmart.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country @JvmOverloads constructor(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("region")
    val region: String = "",
    @SerializedName("code")
    val code: String = "",
    @SerializedName("capital")
    val capital: String = ""
): Parcelable