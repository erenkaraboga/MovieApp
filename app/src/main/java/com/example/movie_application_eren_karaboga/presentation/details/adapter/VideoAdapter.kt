package com.example.movie_application_eren_karaboga.presentation.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.data.models.MovieVideoModel
import com.example.movie_application_eren_karaboga.databinding.GenreItemBinding
import com.example.movie_application_eren_karaboga.databinding.SearchItemBinding

class VideoAdapter(
    val data: List<MovieVideoModel>,
    private val listener: MovieAdapterListener,
) : RecyclerView.Adapter<MovieVideViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieVideViewHolder {
        val binding = GenreItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieVideViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MovieVideViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

class MovieVideViewHolder(
    private val binding: GenreItemBinding,
    private val listener: MovieAdapterListener,
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(item: MovieVideoModel) {
        binding.tvGenreType.text = item.name
    }

    override fun onClick(v: View?) {
        listener.onClickedItem(bindingAdapterPosition)
    }
}

interface MovieAdapterListener {
    fun onClickedItem(position: Int)
}