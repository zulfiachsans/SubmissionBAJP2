package com.dicoding.submissionjetpack2.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class CountryProduction(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)