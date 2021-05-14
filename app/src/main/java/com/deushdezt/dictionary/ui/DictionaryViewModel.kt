package com.deushdezt.dictionary.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deushdezt.dictionary.data.entities.Term
import com.deushdezt.dictionary.repository.TermRepository
import kotlinx.coroutines.launch

class DictionaryViewModel(private val termRepository: TermRepository): ViewModel() {

    val wordInput = MutableLiveData("")
    val descriptionInput = MutableLiveData("")

    val terms = termRepository.findAll()

    private val _showedText = MutableLiveData<String>()
    val showedText: LiveData<String>
        get() = _showedText

    fun onSubmit() {
        viewModelScope.launch {
            if(!wordInput.value.isNullOrEmpty() && !descriptionInput.value.isNullOrEmpty()){
                val newTerm = Term(wordInput.value!!, descriptionInput.value!!)
                termRepository.insertOrUpdate(newTerm)
                wordInput.value = ""
                descriptionInput.value = ""
            }
        }
    }

    fun updateShowedText(newTerms: List<Term>) {
        var newText = ""
        newTerms.forEach {
            newText += "Termino: ${it.word}\n\tDescripcion: ${it.description}\n\n\n"
        }
        Log.d("mine", newText)
        _showedText.value = newText
    }

}