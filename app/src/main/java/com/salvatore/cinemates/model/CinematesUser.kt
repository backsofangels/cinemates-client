package com.salvatore.cinemates.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class CinematesUser() {
    var userId: Long? = null
    var email: String? = null
    var username: String? = null
    var password: String? = null
        @JsonIgnore get
        @JsonProperty set
    var name: String? = null
    var profilePicturePath: String? = null
    var nationality: String? = null
    var preferredLanguage: String? = null
}