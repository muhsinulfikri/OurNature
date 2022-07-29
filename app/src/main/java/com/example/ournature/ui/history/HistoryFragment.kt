package com.example.ournature.ui.history

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ournature.base.BaseFragment
import com.example.ournature.base.Resource
import com.example.ournature.data.local.room.entity.HistoryNews
import com.example.ournature.databinding.FragmentHistoryBinding
import com.example.ournature.ui.history.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<FragmentHistoryBinding, HistoryViewModel>(FragmentHistoryBinding::inflate) {

    private lateinit var adapter: HistoryAdapter
    private var historyNews: List<HistoryNews>? = null

    override fun initView() {
        getListHistory()
    }

    private fun initList() {
        adapter = HistoryAdapter {
            startActivity(context, it.id.toString())
        }
        getViewBinding().rvHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@HistoryFragment.adapter
        }
    }

    private fun getListHistory() {
        getViewModel().getAllHistoryNews()
    }

    override fun observeData() {
        getViewModel().getNewsLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    initList()
                    setDataAdapter(it.data)
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

    private fun setDataAdapter(data: List<HistoryNews>?) {
        data?.let {
            adapter.setItems(it)
            historyNews = it
        }
    }

    fun showContent(isVisible: Boolean) {
        getViewBinding().rvHistory.isVisible = isVisible
    }

    fun showLoading(isVisible: Boolean) {
        getViewBinding().pbLoading.isVisible = isVisible
    }

    fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvMsg.isVisible = isErrorEnabled
        getViewBinding().tvMsg.text = msg
    }

    companion object {
        const val EXTRAS_URL = "EXTRAS_URL"

        @JvmStatic
        fun startActivity(context: Context?, id: String?){
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(id)
            openUrl.putExtra(EXTRAS_URL, id)
            context?.startActivity(openUrl)
        }
    }

}