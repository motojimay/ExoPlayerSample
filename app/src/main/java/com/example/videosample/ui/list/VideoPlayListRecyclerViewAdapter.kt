package com.example.videosample.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videosample.data.model.VideoData
import com.example.videosample.databinding.FragmentVideoPlayListItemBinding

class VideoPlayListRecyclerViewAdapter(private val values: List<VideoData>)
    : RecyclerView.Adapter<VideoPlayListRecyclerViewAdapter.ViewHolder>() {

    private lateinit var listener: OnVideoItemClickListener

    interface OnVideoItemClickListener {
        fun onItemClick(videoData: VideoData)
    }

    fun setOnVideoItemClickListener(listener: OnVideoItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentVideoPlayListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoData = values[position]
        holder.key.text = videoData.key
        holder.videoName.text = videoData.videoName
        holder.videoItem.setOnClickListener {
            listener.onItemClick(videoData)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentVideoPlayListItemBinding): RecyclerView.ViewHolder(binding.root) {
        val videoItem = binding.videoItem
        val key = binding.key
        val videoName = binding.videoName
    }

}