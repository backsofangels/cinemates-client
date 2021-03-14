package com.salvatore.cinemates.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Movie() {
    var tmdbId: Int? = null
    var title: String? = null
    var posterImagePath: String? = null
    var originalLanguage: String? = null

    constructor(tmdbId: Int, title: String, posterImagePath: String, originalLanguage: String) : this() {
        this.tmdbId = tmdbId
        this.title = title
        this.posterImagePath = posterImagePath
        this.originalLanguage = originalLanguage
    }


}