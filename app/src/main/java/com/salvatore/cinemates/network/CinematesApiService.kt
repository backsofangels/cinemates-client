package com.salvatore.cinemates.network

import com.salvatore.cinemates.model.CinematesAuthUserDto
import com.salvatore.cinemates.model.CinematesUser
import com.salvatore.cinemates.model.Movie
import com.salvatore.cinemates.model.MovieSearchResultDto
import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CinematesApiService {
    @GET(NetworkConstants.MOVIE_SEARCH)
    fun searchMovieByTitle(@Query("title") title: String): Observable<ArrayList<MovieSearchResultDto>>

    @GET(NetworkConstants.MOVIE_DETAILS)
    fun getDetailsForMovie(@Query("movieId") movieId: Int): Observable<Movie>

    @POST(NetworkConstants.USER_LOGIN)
    fun performUserLogin(@Body userDto: CinematesAuthUserDto): Observable<Response<CinematesUser>>

    @POST(NetworkConstants.USER_SIGNUP)
    fun performUserSignup(@Body user: CinematesUser): Observable<Response<ResponseBody>>
}