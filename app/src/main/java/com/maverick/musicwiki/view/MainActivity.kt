package com.maverick.musicwiki.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maverick.musicwiki.R
import com.maverick.musicwiki.adapters.MainTagAdapter
import com.maverick.musicwiki.api.ApiInterface
import com.maverick.musicwiki.api.RetrofitObject
import com.maverick.musicwiki.models.tagModel.Tag
import com.maverick.musicwiki.repository.MainRepository
import com.maverick.musicwiki.viewModels.MainViewModel
import com.maverick.musicwiki.viewModels.ViewModelUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainTagAdapter
    private lateinit var tagList: List<Tag>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofitService = ApiInterface.getInstance()
        val mainRepository = MainRepository(retrofitService)

        supportActionBar?.title = "Home"
        recyclerView = findViewById(R.id.recycler_view)
        tagList = ArrayList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainTagAdapter(this, tagList, object : MainTagAdapter.TagClickListener {
            override fun onTagClickListener(position: Int) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("tagName", tagList[position].name)
                startActivity(intent)
            }

        })
        recyclerView.adapter = adapter

        viewModel =
            ViewModelProvider(this, ViewModelUtils(mainRepository))[MainViewModel::class.java]
        viewModel.result.observe(this) {
            tagList = it.toptags.tag
            adapter.tagList = tagList
            adapter.notifyDataSetChanged()
        }
//        getData()
    }

    private fun getData() {
        val apiInterface = RetrofitObject.getInstance().create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            val result = apiInterface.getTopTags()
            if (result.body() != null) {
                tagList = result.body()!!.toptags.tag
                adapter.tagList = tagList
                adapter.notifyDataSetChanged()
                Log.d("tags", result.body().toString())
            } else {
                Log.d("failed tags", "nulls")
            }
        }
    }
}