package com.example.videosample.data.repository

import com.example.videosample.data.model.VideoData
import com.example.videosample.data.model.VideoUrl
import com.example.videosample.data.service.VideoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideoRepository(private val service: VideoService) {

    fun getVideos(): Flow<List<VideoData>> {
        return flow {
            try {
                val response = service.getVideos().execute()
                val value = response.body()?.data ?: emptyList()
                emit(value)
            } catch (e: Exception) {
                // TODO This Error Handling is insufficient. Need to repair.
                emit(emptyList())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getVideoUrl(videoName: String): Flow<VideoUrl> {
        return flow {
            try {
                val response = service.getVideoUrl(videoName).execute()
                val value = response.body() ?: VideoUrl("")
                emit(value)
            } catch (e: Exception) {
                // TODO This Error Handling is insufficient. Need to repair.
                emit(VideoUrl(""))
            }
        }.flowOn(Dispatchers.IO)
    }

}