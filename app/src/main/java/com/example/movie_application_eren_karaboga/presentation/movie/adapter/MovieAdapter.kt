package com.example.movie_application_eren_karaboga.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.base.extensions.loadPosterUrl
import com.example.movie_application_eren_karaboga.databinding.ListItemMovieBinding
import com.example.movie_application_eren_karaboga.data.models.Movie

class MovieAdapter(private val listener: ClickListener) :
    RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    private var data: List<Movie>? = null

    fun setList(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val binding =
            ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(listener, binding)

    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyCustomHolder(
        private val listener: ClickListener,
        private val binding: ListItemMovieBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var movie: Movie

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            this.movie = movie
            binding.tvMovieName.text = movie.originalTitle
            binding.IvMovie.loadPosterUrl(movie.posterPath)
        }

        override fun onClick(p0: View?) {
            listener.click(movie.id)
        }
    }

    interface ClickListener {
        fun click(movieId: Int)
    }

}
