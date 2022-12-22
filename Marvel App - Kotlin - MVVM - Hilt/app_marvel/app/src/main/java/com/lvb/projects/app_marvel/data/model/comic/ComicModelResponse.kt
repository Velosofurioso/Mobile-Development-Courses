package com.lvb.projects.app_marvel.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ComicModelResponse (
    @SerializedName("data")
    val data: ComicModelData
) : Serializable