package com.salvatore.cinemates.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie() {
    var tmdbId: Int? = null
    var title: String? = null
    var posterImagePath: String? = null
    //var originalLanguage: String? = null
    var releaseDate: Date? = null
    var directors: ArrayList<MovieWorker>? = null
    var actors: ArrayList<MovieWorker>? = null

    constructor(tmdbId: Int, title: String, posterImagePath: String, releaseDate: Long, directors: ArrayList<MovieWorker>, actors: ArrayList<MovieWorker>) : this() {
        this.tmdbId = tmdbId
        this.title = title
        this.posterImagePath = posterImagePath
        this.releaseDate = Date(releaseDate)
        //this.originalLanguage = originalLanguage
        this.directors = directors
        this.actors = actors
    }


}