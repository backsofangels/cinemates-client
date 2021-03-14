package com.salvatore.cinemates.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salvatore.cinemates.MainActivity
import com.salvatore.cinemates.R
import com.salvatore.cinemates.model.MovieSearchResultDto

class MovieSearchRecyclerViewViewHolder(view: View, activity: MainActivity): RecyclerView.ViewHolder(view) {
    private val movieTitleTextView: TextView
    private val moviePosterImageView: ImageView
    private var tmdbMovieId: Int? = null
    private val TAG = "MovieSearchRecyclerViewViewHolder"

    init {
        movieTitleTextView = view.findViewById(R.id.searchResultMovieTitle)
        moviePosterImageView = view.findViewById(R.id.searchResultMoviePoster)
        moviePosterImageView.setOnClickListener(
                object: View.OnClickListener {
                    override fun onClick(v: View?) {
                        val movieDetailFragment = MovieDetailFragment()
                        val bundle = Bundle()
                        Log.d(TAG, "tmdbMovieId: ${this@MovieSearchRecyclerViewViewHolder.tmdbMovieId.toString()}")
                        this@MovieSearchRecyclerViewViewHolder.tmdbMovieId?.let { bundle.putInt("tmdbId", it) }
                        Log.d(TAG, "contains: ${bundle.containsKey("tmdbId")}")
                        movieDetailFragment.arguments = bundle
                        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(
                                R.id.mainActivityDrawerLayout,
                                movieDetailFragment,
                                "MovieDetailFragment"
                        )
                        fragmentTransaction.addToBackStack("MovieDetailFragment")
                        fragmentTransaction.commit()
                        activity.hideKeyboard(activity)
                    }

                }
        )
    }

    fun bindItemToViewHolder(movieDto: MovieSearchResultDto) {
        this.movieTitleTextView.text = movieDto.title
        this.tmdbMovieId = movieDto.tmdbId
        Glide.with(itemView).load("https://image.tmdb.org/t/p/w500${movieDto.posterImagePath}").into(this.moviePosterImageView)
    }
}