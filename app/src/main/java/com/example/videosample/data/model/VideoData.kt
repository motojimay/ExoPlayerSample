package com.example.videosample.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoData(
    @SerializedName("key")
    val key: String,
    @SerializedName("video_name_with_format")
    val videoNameWithFormat: String,
    @SerializedName("video_name")
    val videoName: String,
    @SerializedName("file_full_path")
    val fileFullPath: String
) : Parcelable
