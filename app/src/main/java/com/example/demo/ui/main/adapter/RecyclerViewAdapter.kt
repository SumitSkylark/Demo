package com.example.demo.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo.R
import com.example.demo.databinding.AdapterRecyclerViewBinding
import com.example.demo.ui.main.model.ResponseData

class RecyclerViewAdapter(private var arrayList: ArrayList<ResponseData>)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(binding){
                with(arrayList[position]){

                    val context = binding.root.context

                    Glide.with(context)
                        .load(download_url)
                        .encodeQuality(20)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(ivImage)

                    tvTitle.text = author
                    tvDescription.text = download_url
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
