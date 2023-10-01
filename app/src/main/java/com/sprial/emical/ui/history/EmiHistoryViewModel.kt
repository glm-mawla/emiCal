package com.sprial.emical.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sprial.emical.utils.EmiPrefRepository

class EmiHistoryViewModel (
    private val emiPrefRepository: EmiPrefRepository
): ViewModel() {

    val getEmiHistory = liveData {
        emit(emiPrefRepository.fetchDefaultEmiHistory())
    }
}

class EmiViewModelFactory(
    private val emiPrefRepository: EmiPrefRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmiHistoryViewModel::class.java)) {
            return EmiHistoryViewModel(emiPrefRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}