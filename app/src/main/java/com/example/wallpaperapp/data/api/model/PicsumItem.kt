package com.example.wallpaperapp.data.api.model

import com.google.gson.annotations.SerializedName


//Model class is use to convert the json data to kotlin object
data class PicsumItem(
    val author: String?,
    //GSon annotation
    @SerializedName("download_url")
    val downloadUrl: String?,
    val height: Int?,
    val id: String?,
    val url: String?,
    val width: Int?
)