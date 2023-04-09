package com.example.movie_application_eren_karaboga.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.base.extensions.loadPosterUrl
import com.example.movie_application_eren_karaboga.databinding.ListItemMovieBinding
import com.example.movie_application_eren_karaboga.data.models.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    private var data: List<Movie>? = null

    fun setList(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyCustomHolder {
        val binding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    inner class MyCustomHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.tvMovieName.text = data.originalTitle
            binding.IvMovie.loadPosterUrl(data.posterPath)
        }
    }

}