package com.salvatore.cinemates.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.salvatore.cinemates.R
import com.salvatore.cinemates.databinding.FragmentDiscoverBinding
import com.salvatore.cinemates.model.MovieSearchResultDto
import com.salvatore.cinemates.network.NetworkApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class DiscoverFragment: Fragment(R.layout.fragment_discover) {
    private var _binding: FragmentDiscoverBinding? = null
    private val binding get() = _binding!!
    private var recyclerViewAdapter: MovieSearchRecyclerViewAdapter = MovieSearchRecyclerViewAdapter(
        ArrayList()
    )
    private val TAG = "DiscoverFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        this.binding.discoverFragmentDiscoverMoviesTextView.visibility = View.GONE
        this.binding.discoverFragmentDiscoverMoviesRecyclerView.layoutManager = GridLayoutManager(
            this.activity?.applicationContext, GridLayoutManager.VERTICAL)
        this.binding.discoverFragmentDiscoverMoviesRecyclerView.adapter = recyclerViewAdapter
        this.binding.discoverFragmentMovieSearchView.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrBlank()) {
                        return false
                    }
                    val observable = NetworkApiService.searchApiCall().searchMovieByTitle(query)
                    observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                                networkResponse -> run {
                                    this@DiscoverFragment.recyclerViewAdapter.updateDataset(networkResponse)
                                }
                        }, {
                            error -> Log.e(TAG, error.message!!)
                        })
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            }
        )
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}