package com.salvatore.cinemates.model

class CinematesAuthUserDto() {
    var username: String? = null
    var password: String? = null


    constructor(username: String, password: String) : this() {
        this.username = username
        this.password = password
    }
}