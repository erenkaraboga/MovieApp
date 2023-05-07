package com.example.movie_application_eren_karaboga.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.extensions.loadFullImage
import com.example.movie_application_eren_karaboga.data.models.Movie
import com.example.movie_application_eren_karaboga.databinding.GenreItemBinding
import com.example.movie_application_eren_karaboga.databinding.ListItemMovieBinding
import com.example.movie_application_eren_karaboga.databinding.SearchItemBinding


class SearchAdapter :
    RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {

    private var movieList: List<Movie>? = null
    fun setList(data: List<Movie>) {
        movieList = data
        notifyDataSetChanged()
    }
    class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieName.text = movie.title
            binding.tvOverView.text = movie.overview
            binding.TvVoteText.text = movie.voteAverage.toString()

            Glide.with(binding.IvMovie).load(movie.posterPath?.loadFullImage())
                .transform(CenterInside(), RoundedCorners(30)).placeholder(R.drawable.loading_image).error(
                    R.drawable.error_image)
                .into(binding.IvMovie)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList?.get(position)?.let { holder.bind(it) }
    }
    override fun getItemCount(): Int = movieList?.size ?: 0
}