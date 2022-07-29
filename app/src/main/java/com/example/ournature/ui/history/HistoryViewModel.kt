package com.example.ournature.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ournature.base.Resource
import com.example.ournature.data.local.room.entity.HistoryNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: HistoryRepository): ViewModel() {
    private val allNewsLiveData = MutableLiveData<Resource<List<HistoryNews>>>()

    fun getNewsLiveData(): LiveData<Resource<List<HistoryNews>>> = allNewsLiveData

    fun getAllHistoryNews(){
        allNewsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO){
            try {
                val historyNews = repository.getAllHistoryNews()
                viewModelScope.launch(Dispatchers.Main){
                    allNewsLiveData.value = Resource.Success(historyNews)
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main){
                    allNewsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}