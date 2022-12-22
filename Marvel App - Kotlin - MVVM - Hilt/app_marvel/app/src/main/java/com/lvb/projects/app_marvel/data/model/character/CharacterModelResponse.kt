package com.lvb.projects.app_marvel.data.model.character

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CharacterModelResponse (
    @SerializedName("data")
    val data: CharacterModelData
) : Serializable