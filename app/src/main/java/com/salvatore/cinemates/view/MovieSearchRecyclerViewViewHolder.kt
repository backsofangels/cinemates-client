package com.salvatore.cinemates.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salvatore.cinemates.R
import com.salvatore.cinemates.model.MovieSearchResultDto

class MovieSearchRecyclerViewViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val movieTitleTextView: TextView
    private val moviePosterImageView: ImageView
    private val TAG = "MovieSearchRecyclerViewViewHolder"

    init {
        movieTitleTextView = view.findViewById(R.id.searchResultMovieTitle)
        moviePosterImageView = view.findViewById(R.id.searchResultMoviePoster)
    }

    fun bindItemToViewHolder(movieDto: MovieSearchResultDto) {
        this.movieTitleTextView.text = movieDto.title
        Glide.with(itemView).load("https://image.tmdb.org/t/p/w500${movieDto.posterImagePath}").into(this.moviePosterImageView)
    }
}