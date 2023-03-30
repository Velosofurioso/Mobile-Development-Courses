package com.lvb.courses.app_organizze.model

data class User(
    var name: String,
    var email: String,
    var password: String
) {
    constructor() : this("", "", "")
}
