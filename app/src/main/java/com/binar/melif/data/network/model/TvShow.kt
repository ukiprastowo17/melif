package com.binar.melif.data.network.model


import com.google.gson.annotations.SerializedName

data class TvShow(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvShowResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)