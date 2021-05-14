package com.deushdezt.dictionary.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deushdezt.dictionary.repository.TermRepository

class DictionaryViewModelFactory(private val termRepository: TermRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DictionaryViewModel::class.java)){
            return DictionaryViewModel(termRepository) as T
        }

        throw Exception("Unknown view model type")
    }
}