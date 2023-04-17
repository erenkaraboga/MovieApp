package com.example.movie_application_eren_karaboga.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.data.models.Movie
import com.example.movie_application_eren_karaboga.databinding.GenreItemBinding

class SearchAdapter(private val movieList: List<Movie>) :
    RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvGenreType.text = movie.originalTitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GenreItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}