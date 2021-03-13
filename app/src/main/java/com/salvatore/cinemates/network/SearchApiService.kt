package com.salvatore.cinemates.network

import com.salvatore.cinemates.model.MovieSearchResultDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {
    @GET(NetworkConstants.MOVIE_SEARCH)
    fun searchMovieByTitle(@Query("title") title: String): Observable<ArrayList<MovieSearchResultDto>>
}