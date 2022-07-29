package com.example.ournature.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ournature.R
import com.example.ournature.databinding.ActivityMainBinding
import com.example.ournature.ui.history.HistoryFragment
import com.example.ournature.ui.news.NewsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView()

        val newsFragment = NewsFragment()
        val historyFragment = HistoryFragment()
        //val bookMarkFragment = BookMarkFragment()

        setCurrentFragment(newsFragment)
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list_news -> setCurrentFragment(newsFragment)
                R.id.history -> setCurrentFragment(historyFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,fragment)
            commit()
        }

    private fun bindingView(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}