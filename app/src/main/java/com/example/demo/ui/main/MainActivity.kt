package com.example.demo.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.databinding.BottomLayoutDescriptionBinding
import com.example.demo.model.ResponseEvent
import com.example.demo.ui.main.adapter.RecyclerViewAdapter
import com.example.demo.ui.main.model.ResponseData
import com.example.demo.ui.main.view_model.MainVMFactory
import com.example.demo.ui.main.view_model.MainViewModel
import com.example.demo.utilities.Utilities.Companion.checkForInternet
import com.example.demo.utilities.Utilities.Companion.showToast
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MainVMFactory(this))[MainViewModel::class.java]

        setSupportActionBar(binding.toolbar)

        setUIViews()
        collectionDataUI()
    }

    private fun setUIViews() {
        if (checkForInternet(this)){
            getList()
        } else {
            showToast(this, getString(R.string.no_internet))
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            if (checkForInternet(this)){
                getList()
            } else {
                showToast(this, getString(R.string.no_internet))
            }
            Handler(Looper.getMainLooper()).postDelayed(
                { binding.swipeRefreshLayout.isRefreshing = false },
                1000
            )
        }
    }

    private fun getList() {
        viewModel.getList()
    }

    private fun collectionDataUI() {
        lifecycleScope.launch {
            viewModel.uiStateFlow.collect {
                when (it) {
                    is ResponseEvent.NoData -> {}

                    is ResponseEvent.ResponseEventData -> {
                        @Suppress("UNCHECKED_CAST")
                        showList(it.data as ArrayList<ResponseData>)
                    }
                    is ResponseEvent.Failure -> {

                    }
                    is ResponseEvent.RemoveLoader -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    else -> {}
                }
            }
        }
    }

    private fun showList(dataList: ArrayList<ResponseData>) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = RecyclerViewAdapter(dataList, this@MainActivity)
        }
    }

    private fun removeLoader() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onItemClick(item: ResponseData) {
        val inflater = LayoutInflater.from(this)
        val mBinding = BottomLayoutDescriptionBinding.inflate(inflater)
        val dialog = BottomSheetDialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.setContentView(mBinding.root)
        dialog.show()

        mBinding.tvTitle.text = item.author
        mBinding.tvDescription.text = item.download_url
    }
}