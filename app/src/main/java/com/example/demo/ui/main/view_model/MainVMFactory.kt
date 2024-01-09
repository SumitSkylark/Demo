package com.example.demo.ui.main.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainVMFactory (val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                MainRepository.getInstance(context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}