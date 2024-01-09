package com.example.demo.ui.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.model.ResponseEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel(){

    private val _uiStateFlow = MutableStateFlow<ResponseEvent>(ResponseEvent.Loader)
    val uiStateFlow: StateFlow<ResponseEvent> = _uiStateFlow
    fun getList(){
        viewModelScope.launch {
            val response = repository.getList()
            when (response?.isSuccessful == true) {
                true -> {
                    val responseBody = response?.body()
                    _uiStateFlow.emit(ResponseEvent.ResponseEventData(responseBody!!))
                }
                false -> {
                    _uiStateFlow.emit(
                        ResponseEvent.Failure(
                            response?.message() ?: ""
                        )
                    )
                }
            }
            _uiStateFlow.emit(ResponseEvent.RemoveLoader)
        }
    }
}