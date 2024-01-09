package com.example.demo.utilities

class Constants {
    companion object{
        const val SPLASH_LOADING = 5000
        const val ANIMATION_LOADING = 1500

        const val BASE_URL = "https://picsum.photos/"
        private const val MIDDLE_PATH_URL = "v2/"
        const val URL_LIST_VIEW = BASE_URL + MIDDLE_PATH_URL +"list?page=2&limit=20"
    }
}