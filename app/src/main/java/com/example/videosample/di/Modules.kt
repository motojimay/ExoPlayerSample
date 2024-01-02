package com.example.videosample.di

import com.example.videosample.data.repository.VideoRepository
import com.example.videosample.data.service.VideoService
import com.example.videosample.ui.list.VideoPlayListViewModel
import com.example.videosample.ui.video.VideoPlayViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO  use your IP Address
const val LOCAL_IP = "http://192.168.1.4"
const val PORT = "3000"

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("${LOCAL_IP}:${PORT}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(VideoService::class.java)
    }

    single {
        VideoRepository(get())
    }

    viewModel {
        VideoPlayListViewModel(get())
    }

    viewModel {
        VideoPlayViewModel(get())
    }

}
