package com.lvb.projects.app_marvel.data.model.comic

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ComicModelData (
    @SerializedName("results")
    val results: List<ComicModel>
) : Serializable