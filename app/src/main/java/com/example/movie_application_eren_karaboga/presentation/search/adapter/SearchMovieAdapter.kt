package com.example.movie_application_eren_karaboga.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.data.models.Movie
import com.example.movie_application_eren_karaboga.databinding.GenreItemBinding
import com.example.movie_application_eren_karaboga.databinding.SearchItemBinding


class SearchAdapter :
    RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {

    private var movieList: List<Movie>? = null
    fun setList(data: List<Movie>) {
        movieList = data
        notifyDataSetChanged()
    }
    class MovieViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvGenreType.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList?.get(position)?.let { holder.bind(it) }
    }
    override fun getItemCount(): Int = movieList?.size ?: 0
}