package com.example.videosample.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.videosample.data.model.VideoData
import com.example.videosample.data.repository.VideoRepository

class VideoPlayListViewModel(videoRepository: VideoRepository): ViewModel() {
    val videos: LiveData<List<VideoData>> = videoRepository.getVideos().asLiveData()
}