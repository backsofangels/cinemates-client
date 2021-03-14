package com.salvatore.cinemates.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.FragmentDiscoverBinding
import com.salvatore.cinemates.databinding.FragmentMovieDetailBinding
import com.salvatore.cinemates.model.Movie
import com.salvatore.cinemates.network.NetworkApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailFragment: Fragment(R.layout.fragment_movie_detail) {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private var movie: Movie? = null
    private val TAG = "MovieDetailFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Sanity check on bundle
        if (arguments != null && arguments!!.containsKey("tmdbId")) {
            Log.d(TAG, "savedInstanceState not null")
            var tmdbMovieId = arguments!!.getInt("tmdbId")
            val movieDetailsObservable = NetworkApiService.searchApiCall().getDetailsForMovie(tmdbMovieId)
            movieDetailsObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        networkResponse -> run {
                            this.movie = networkResponse
                            this.binding.movieTitleTextView.text = this.movie!!.title
                            Glide.with(this).load("https://image.tmdb.org/t/p/w500${this.movie!!.posterImagePath}").into(this.binding.movieDetailPosterImageview)
                        }
                    }, {
                        error -> Log.e(TAG, error.message!!)
                    })
        } else Log.e(TAG, "BUNDLE NULL")
        super.onViewCreated(view, savedInstanceState)
    }
}