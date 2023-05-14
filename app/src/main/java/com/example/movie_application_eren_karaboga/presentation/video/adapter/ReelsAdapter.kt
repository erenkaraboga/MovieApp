package com.example.movie_application_eren_karaboga.presentation.video.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.data.models.MovieVideoModel
import com.example.movie_application_eren_karaboga.databinding.ListVideoItemBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class ReelsAdapter(
    private val movieVideoModelList: List<MovieVideoModel>,
) : RecyclerView.Adapter<YoutubePlayerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): YoutubePlayerViewHolder {
        val binding = ListVideoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return YoutubePlayerViewHolder(binding, movieVideoModelList)
    }

    override fun onBindViewHolder(holder: YoutubePlayerViewHolder, position: Int) =
        holder.bind()

    override fun getItemCount(): Int = movieVideoModelList.size
}

class YoutubePlayerViewHolder(
    private var binding: ListVideoItemBinding,
    private val data: List<MovieVideoModel>,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayer
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val key = data[bindingAdapterPosition].key
                youTubePlayer.loadVideo(key, 0f)
            }
        })
    }
}