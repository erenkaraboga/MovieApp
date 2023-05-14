package com.example.movie_application_eren_karaboga.presentation.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.movie_application_eren_karaboga.presentation.video.adapter.ReelsAdapter
import com.example.movie_application_eren_karaboga.data.models.MovieVideoModel
import com.example.movie_application_eren_karaboga.databinding.FragmentVideoBinding


class VideoFragment(private val movieList: List<MovieVideoModel>, private val position: Int) :
    Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var reelsAdapter: ReelsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentVideoBinding.inflate(layoutInflater)
        initManager()
        return binding.root
    }

    private fun initManager() {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.baseRecyclerView.layoutManager = layoutManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initReelsAdapter(movieList, position)
    }

    private fun initReelsAdapter(videoList: List<MovieVideoModel>, position: Int) {
        reelsAdapter = ReelsAdapter(videoList)
        binding.baseRecyclerView.adapter = reelsAdapter
        binding.baseRecyclerView.scrollToPosition(position)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.baseRecyclerView)
    }
}



