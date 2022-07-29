package com.example.ournature.data.api.response

import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("articles")
    var articles: List<Article>
)