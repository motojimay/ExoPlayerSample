package com.example.videosample.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videosample.data.model.VideoData
import com.example.videosample.databinding.FragmentVideoPlayListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoPlayListFragment: Fragment() {

    private val viewModel: VideoPlayListViewModel by viewModel()
    private lateinit var binding: FragmentVideoPlayListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoPlayListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModelObserver()
    }

    private fun setUpViewModelObserver() {
        viewModel.videos.observe(viewLifecycleOwner) {
            val adapter = VideoPlayListRecyclerViewAdapter(it)
            binding.recyclerView.adapter = adapter
            setUpVideoItemClickListener(adapter)
        }
    }

    private fun setUpVideoItemClickListener(adapter: VideoPlayListRecyclerViewAdapter) {
        adapter.setOnVideoItemClickListener(object : VideoPlayListRecyclerViewAdapter.OnVideoItemClickListener {
            override fun onItemClick(videoData: VideoData) {
                val action = VideoPlayListFragmentDirections.actionVideoListViewToVideoPlayView(videoData)
                findNavController().navigate(action)
            }
        })
    }

}