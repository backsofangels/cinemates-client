package com.salvatore.cinemates.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class MovieWorker() {
    var personId: Int? = null
    var name: String? = null
    var gender: Int? = null
    var profilePicturePath: String? = null
    var adult: Boolean? = false
    var popularity: Double? = null
    var knownForDepartment: String? = null

    constructor(personId: Int, name: String, gender: Int, profilePicturePath: String, adult: Boolean, popularity: Double, knownForDepartment: String) : this() {
        this.personId = personId
        this.name = name
        this.gender = gender
        this.profilePicturePath = profilePicturePath
        this.adult = adult
        this.popularity = popularity
        this.knownForDepartment = knownForDepartment
    }
}