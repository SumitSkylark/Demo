package com.example.demo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.model.ResponseEvent
import com.example.demo.ui.main.adapter.RecyclerViewAdapter
import com.example.demo.ui.main.model.ResponseData
import com.example.demo.ui.main.view_model.MainVMFactory
import com.example.demo.ui.main.view_model.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MainVMFactory(this)
        )[MainViewModel::class.java]

        setSupportActionBar(binding.toolbar)

        viewModel.getList()
        collectionDataUI()
    }

    private fun collectionDataUI() {
        lifecycleScope.launch {
            viewModel.uiStateFlow.collect {
                when (it) {
                    is ResponseEvent.NoData -> {}

                    is ResponseEvent.ResponseEventData -> {
                        showList(it.data as ArrayList<ResponseData>)
                    }
                    is ResponseEvent.Failure -> {

                    }
                    is ResponseEvent.RemoveLoader -> {

                    }
                    else -> {}
                }
            }
        }
    }

    private fun showList(dataList: ArrayList<ResponseData>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerViewAdapter(dataList)
        }
    }
}