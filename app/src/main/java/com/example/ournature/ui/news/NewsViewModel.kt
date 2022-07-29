package com.example.ournature.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ournature.base.Resource
import com.example.ournature.data.api.response.Article
import com.example.ournature.data.api.response.Source
import com.example.ournature.data.local.room.entity.HistoryNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
    @Inject constructor(private val repository: NewsRepository): ViewModel() {

    private val newsLiveData = MutableLiveData<Resource<List<Article>>>()
    private val insertHistoryNews = MutableLiveData<Resource<Int>>()
    private val historyLiveData = MutableLiveData<Resource<Article>>()

    fun getNewsList(){
        newsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.getNewsList()
                viewModelScope.launch(Dispatchers.Main){
                    newsLiveData.value = response.articles.let { Resource.Success(it) }
                }
            } catch (e:Exception){
                viewModelScope.launch(Dispatchers.Main){
                    newsLiveData.value = Resource.Error(e.message.toString())
                }
            }
        }
    }

    fun insertHistoryNews(historyNews: HistoryNews){
        insertHistoryNews.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = repository.insertHistoryNews(historyNews)
                viewModelScope.launch(Dispatchers.Main){
                    insertHistoryNews.value = Resource.Success(response)
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main){
                    insertHistoryNews.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    fun getNewsListLiveData(): LiveData<Resource<List<Article>>> = newsLiveData
    fun setHistoryLiveData(): LiveData<Resource<Article>> = historyLiveData
}