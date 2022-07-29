package com.example.ournature.data.api.response

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("source")
    var source: Source? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var image: String? = null
)