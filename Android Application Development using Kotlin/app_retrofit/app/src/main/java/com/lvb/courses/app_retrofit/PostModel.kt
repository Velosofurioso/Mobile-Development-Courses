package com.lvb.courses.app_retrofit

import com.google.gson.annotations.SerializedName

class PostModel {

    @SerializedName("id")
    var id : Int = 0

    @SerializedName("userId")
    var userId : Int = 0

    @SerializedName("title")
    var title : String = ""

    @SerializedName("body")
    var body : String = ""
}