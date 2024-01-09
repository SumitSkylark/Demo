package com.example.demo.ui.main.model

import androidx.annotation.Keep

@Keep
data class ResponseData(
    val id: String,
    val author: String,
    val width: String,
    val height: String,
    val url: String,
    val download_url: String
)