package com.salvatore.cinemates.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MovieSearchResultDto() {
    var tmdbId: Int? = null
    var title: String? = null
    var posterImagePath: String? = null

    constructor(tmdbId: Int, title: String, posterImagePath: String) : this() {
        this.tmdbId = tmdbId
        this.title = title
        this.posterImagePath = posterImagePath
    }

    override fun toString(): String {
        return "MovieSearchResultDto:\ntmdbId: $tmdbId\ntitle: $title\nposterImagePath: $posterImagePath\n"
    }
}