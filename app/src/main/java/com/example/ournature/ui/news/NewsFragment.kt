package com.example.ournature.ui.news

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ournature.base.BaseFragment
import com.example.ournature.base.Resource
import com.example.ournature.data.api.response.Article
import com.example.ournature.data.local.room.entity.HistoryNews
import com.example.ournature.databinding.FragmentNewsBinding
import com.example.ournature.ui.news.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment: BaseFragment<FragmentNewsBinding, NewsViewModel>(FragmentNewsBinding::inflate){

    private var TAG = NewsFragment::class.simpleName
    private lateinit var adapter : NewsAdapter
    private lateinit var historyNews : HistoryNews
    private var isHistory: Boolean = false

    private fun setAdapter(data: List<Article>?){
        data?.let { adapter.setItems(it) }
    }

    override fun initView() {
        getData()
//        initSwipeRefresh()
    }

    private fun initList(){
        adapter = NewsAdapter {
            startActivity(context, it.url)
        }

        getViewBinding().rvContent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@NewsFragment.adapter
        }

    }

    fun addNewsToHistory(news: Article){
        getViewBinding().rvContent.setOnClickListener{
            Toast.makeText(context, "succes", Toast.LENGTH_SHORT).show()
            Log.d(TAG,"success")
            isHistory = !isHistory
            if (isHistory){
                historyNews = HistoryNews(news.source?.id?.toInt(), news.source?.name, news.title)
            }
        }
        historyNews.let {
            getViewModel().insertHistoryNews(it)
        }
    }

//    private fun initSwipeRefresh(){
//        getViewBinding().srlContent.setOnRefreshListener {
//            getViewBinding().srlContent.isRefreshing = false
//            getData()
//        }
//    }

    private fun getData(){
        getViewModel().getNewsList()
    }

    override fun observeData() {
        getViewModel().getNewsListLiveData().observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let { data ->
                        if (data.isEmpty()){
                            showError(true, "Data Cannot Load")
                            showContent(false)
                        } else {
                            showContent(true)
                            showError(false, null)
                            initList()
                            setAdapter(it.data)
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
        getViewModel().setHistoryLiveData().observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    it.data?.let { data ->
                        addNewsToHistory(it.data)
                    }
                    showError(false, null)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    fun showContent(isVisible: Boolean){
        getViewBinding().rvContent.isVisible = isVisible
    }

    fun showLoading(isVisible: Boolean){
        getViewBinding().pbLoading.isVisible = isVisible
    }

    fun showError(isErrorEnabled: Boolean, msg: String?){
        getViewBinding().tvMsg.isVisible = isErrorEnabled
        getViewBinding().tvMsg.text = msg
    }

    companion object {
        const val EXTRAS_URL = "EXTRAS_URL"

        @JvmStatic
        fun startActivity(context: Context?, url: String?){
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(url)
            openUrl.putExtra(EXTRAS_URL, url)
            context?.startActivity(openUrl)
        }
    }
}