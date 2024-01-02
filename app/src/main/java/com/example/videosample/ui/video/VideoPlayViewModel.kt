package com.example.videosample.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.videosample.data.model.VideoUrl
import com.example.videosample.data.repository.VideoRepository

class VideoPlayViewModel(private val videoRepository: VideoRepository): ViewModel() {
    lateinit var videoUrl: LiveData<VideoUrl>
    fun getVideoUrlByVideoName(videoName: String) {
        videoUrl = videoRepository.getVideoUrl(videoName).asLiveData()
    }
}
