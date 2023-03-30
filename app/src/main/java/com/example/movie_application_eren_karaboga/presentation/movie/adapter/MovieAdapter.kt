package com.example.movie_application_eren_karaboga.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.domain.models.Movie
import com.example.movie_application_eren_karaboga.domain.models.Result

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MyCustomHolder>() {

    var liveData: List<Result>? = null;

    fun setList(liveData: List<Result>) {
        this.liveData = liveData
        notifyDataSetChanged()
    }

    class MyCustomHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.findViewById<TextView>(R.id.title)
        fun bind(data: Result) {
            txtTitle.text = data.title;
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