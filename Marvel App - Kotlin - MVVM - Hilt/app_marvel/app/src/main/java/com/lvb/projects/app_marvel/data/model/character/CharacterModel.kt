package com.lvb.projects.app_marvel.data.model.character

import com.google.gson.annotations.SerializedName
import com.lvb.projects.app_marvel.data.model.ThumbnailModel
import java.io.Serializable

data class CharacterModel (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("description")
    val thumbnailModel: ThumbnailModel
): Serializable