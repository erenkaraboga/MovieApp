package com.example.movie_application_eren_karaboga.presentation.details.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_application_eren_karaboga.data.models.Genre
import com.example.movie_application_eren_karaboga.databinding.GenreItemBinding

class GenreAdapter :
    RecyclerView.Adapter<GenreAdapter.MyCustomHolder>() {

    private var data: List<Genre>? = null

    fun setList(data: List<Genre>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val binding =
            GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(binding)

    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyCustomHolder(
        private val binding: GenreItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var genre: Genre


        fun bind(genre: Genre) {
            this.genre = genre
            binding.tvGenreType.text = genre.name
        }

    }
}
