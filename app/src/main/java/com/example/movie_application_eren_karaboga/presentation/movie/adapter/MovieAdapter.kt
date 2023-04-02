package com.example.movie_application_eren_karaboga.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.utils.Constants
import com.example.movie_application_eren_karaboga.domain.models.Movie
import com.example.movie_application_eren_karaboga.domain.models.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    var liveData: List<Result>? = null;

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieName = view.findViewById<TextView>(R.id.movieNameTv)
        val genre = view.findViewById<TextView>(R.id.genreTv)
        val duration = view.findViewById<TextView>(R.id.durationTv)
        val rate = view.findViewById<TextView>(R.id.ratingTv)
        val posterView = view.findViewById<ImageView>(R.id.posterIv)


        fun bind(data: Result) {
            movieName.text = data.title
            duration.text = data.releaseDate
            rate.text = data.voteAverage.toString()
            genre.text = data.originalLanguage
            Glide.with(posterView).load(Constants.posterUrl + data.posterPath)
                .into(posterView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyCustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyCustomHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyCustomHolder, position: Int) {
        holder.bind(liveData!!.get(position))
    }

    override fun getItemCount(): Int {
        if (liveData == null) {
            return 0
        } else return liveData!!.size
    }
}