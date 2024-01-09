package com.example.demo.ui.main.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.demo.R
import com.example.demo.databinding.AdapterRecyclerViewBinding
import com.example.demo.ui.main.model.ResponseData

class RecyclerViewAdapter(private var arrayList: ArrayList<ResponseData>,
                          private val itemClickListener: OnItemClickListener)
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
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC )
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>,
                                isFirstResource: Boolean
                            ): Boolean {
                                progressBar.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable,
                                model: Any,
                                target: Target<Drawable>?,
                                dataSource: DataSource,
                                isFirstResource: Boolean
                            ): Boolean {
                                progressBar.visibility = View.GONE
                                return false
                            }
                        })
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(ivImage)

                    tvTitle.text = author
                    tvDescription.text = download_url

                    itemView.setOnClickListener {
                        itemClickListener.onItemClick(this)
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: ResponseData)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}
