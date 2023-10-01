package com.sprial.emical.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sprial.emical.data.EmiInfoModel
import com.sprial.emical.utils.EmiPrefRepository
import com.sprial.emical.utils.EmiTheme
import kotlinx.coroutines.launch

class MainViewModel(
    private val emiPrefRepository: EmiPrefRepository
) : ViewModel() {

    val defaultEmiTHeme = liveData {
        emit(emiPrefRepository.fetchDefaultEmiTheme())
    }

    fun updateEmiTheme(emiTheme: EmiTheme) {
        viewModelScope.launch {
            emiPrefRepository.updateEmiTheme(emiTheme)
        }
    }

    fun updateEmiHistory(emiInfoModel: EmiInfoModel) {
        viewModelScope.launch {
            emiPrefRepository.updateEmiHistory(emiInfoModel)
        }
    }
}

class MainViewModelFactory(
    private val emiPrefRepository: EmiPrefRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(emiPrefRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}