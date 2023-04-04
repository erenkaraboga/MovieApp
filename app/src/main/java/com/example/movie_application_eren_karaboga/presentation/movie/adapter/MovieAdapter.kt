package com.example.movie_application_eren_karaboga.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.databinding.MovieItemBinding
import com.example.movie_application_eren_karaboga.domain.models.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    private var liveData: List<Result>? = null

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    inner class MyCustomHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Result) {
            binding.movieNameTv.text = data.title
            binding.durationTv.text = data.releaseDate
            binding.ratingTv.text = data.voteAverage.toString()
            binding.genreTv.text = data.originalLanguage
            Glide.with(binding.posterIv).load(Constants.posterUrl + data.posterPath).into(binding.posterIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyCustomHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder, position: Int) {
        liveData?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = liveData?.size ?: 0
}