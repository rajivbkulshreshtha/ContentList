package com.example.contentlist.data.model


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    var id: Int?, // 21
    @SerializedName("name")
    var name: String?, // Rear Window
    @SerializedName("poster-image")
    var posterImage: String? // poster5.jpg
)
