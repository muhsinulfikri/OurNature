package com.example.ournature.data.api.datasource

import com.example.ournature.data.api.response.NewsListResponse
import com.example.ournature.data.api.services.ApiServices

class NewsListDataSource(private val newsApiServices: ApiServices?) {

    suspend fun getNewsList(): NewsListResponse {
        return newsApiServices!!.getNewsList()
    }
}