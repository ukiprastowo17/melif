package com.binar.melif.data.network.model

import com.google.gson.annotations.SerializedName

data class MelifModel(
    @SerializedName("results")
    val results: List<MelifResultModel>
)

data class MelifResultModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("name")
    val name: String
)