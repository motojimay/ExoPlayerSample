package com.example.videosample.data.service;

import com.example.videosample.data.model.Result
import com.example.videosample.data.model.VideoUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {

    @GET("/videos")
    fun getVideos(): Call<Result>

    @GET("/video/sample/{videoName}")
    fun getVideoUrl(@Path("videoName") videoName: String): Call<VideoUrl>

}
