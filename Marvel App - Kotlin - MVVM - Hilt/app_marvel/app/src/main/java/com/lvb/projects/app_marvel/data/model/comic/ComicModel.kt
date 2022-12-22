package com.lvb.projects.app_marvel.data.model.comic

import com.google.gson.annotations.SerializedName
import com.lvb.projects.app_marvel.data.model.ThumbnailModel

data class ComicModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel,
)

