package com.example.movie_application_eren_karaboga.presentation.dashboard.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movie_application_eren_karaboga.R
import com.example.movie_application_eren_karaboga.base.extensions.loadFullImage
import com.example.movie_application_eren_karaboga.data.models.Movie
import com.example.movie_application_eren_karaboga.databinding.ListItemDashboardBinding


class DashboardAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<DashboardAdapter.MyCustomHolder>() {

    private var data: List<Movie>? = null

    fun setList(data: List<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomHolder {
        val binding =
            ListItemDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyCustomHolder(listener, binding)

    }

    override fun onBindViewHolder(holder: MyCustomHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    class MyCustomHolder(
        private val listener: OnClickListener,
        private val binding: ListItemDashboardBinding,
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var movie: Movie

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            this.movie = movie
            binding.tvMovieName.text = movie.originalTitle
            Glide.with(binding.IvMovie).load(movie.posterPath?.loadFullImage())
                .transform(CenterInside(), RoundedCorners(30)).placeholder(R.drawable.loading_image).error(
                    R.drawable.error_image)
                .into(binding.IvMovie)
        }

        override fun onClick(p0: View?) {
            listener.onclick(movie.id)
        }
    }

    interface OnClickListener {
        fun onclick(movieId: Int)
    }

}
