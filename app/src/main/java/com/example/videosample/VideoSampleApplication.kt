package com.example.videosample;

import android.app.Application
import com.example.videosample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VideoSampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VideoSampleApplication)
            modules(appModule)
        }
    }
}
