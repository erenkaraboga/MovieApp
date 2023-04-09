package com.example.movie_application_eren_karaboga.base.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.movie_application_eren_karaboga.base.utils.Constants


fun ImageView.loadPosterUrl(posterPath: String) {
    val posterUrl = Constants.posterUrl + posterPath
    Glide.with(this)
        .load(posterUrl)
        .transform(CenterInside(), RoundedCorners(30))
        .into(this)
}
