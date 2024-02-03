package com.sprial.emical.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sprial.emical.data.EmiInfoModel
import com.sprial.emical.utils.EmiPrefManager
import com.sprial.emical.utils.EmiTheme
import kotlinx.coroutines.launch

class MainViewModel(
    private val emiPrefManager: EmiPrefManager
) : ViewModel() {

    val defaultEmiTHeme = liveData {
        emit(emiPrefManager.fetchDefaultEmiTheme())
    }

    fun updateEmiTheme(emiTheme: EmiTheme) {
        viewModelScope.launch {
            emiPrefManager.updateEmiTheme(emiTheme)
        }
    }

    fun updateEmiHistory(emiInfoModel: EmiInfoModel) {
        viewModelScope.launch {
            emiPrefManager.updateEmiHistory(emiInfoModel)
        }
    }
}

class MainViewModelFactory(
    private val emiPrefManager: EmiPrefManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(emiPrefManager) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}