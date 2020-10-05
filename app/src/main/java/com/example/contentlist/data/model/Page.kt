package com.example.contentlist.data.model


import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("content-items")
    var contentItems: ContentItems?,
    @SerializedName("page-num")
    var pageNum: String?, // 2
    @SerializedName("page-size")
    var pageSize: String?, // 20
    @SerializedName("title")
    var title: String?, // Romantic Comedy
    @SerializedName("total-content-items")
    var totalContentItems: String? // 54
)
