package com.sprial.emical.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sprial.emical.utils.EmiPrefManager

class EmiHistoryViewModel (
    private val emiPrefManager: EmiPrefManager
): ViewModel() {

    val getEmiHistory = liveData {
        emit(emiPrefManager.fetchDefaultEmiHistory())
    }
}

class EmiViewModelFactory(
    private val emiPrefManager: EmiPrefManager
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmiHistoryViewModel::class.java)) {
            return EmiHistoryViewModel(emiPrefManager) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}