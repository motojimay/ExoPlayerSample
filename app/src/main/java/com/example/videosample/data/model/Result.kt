package com.example.videosample.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("result")
    val result: String,
    @SerializedName("data")
    val data: List<VideoData>
)
