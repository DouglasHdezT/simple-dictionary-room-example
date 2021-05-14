package com.deushdezt.dictionary.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deushdezt.dictionary.data.entity.Term
import com.deushdezt.dictionary.repository.TermRepository
import kotlinx.coroutines.launch

class DictionaryViewModel(private val termRepository: TermRepository): ViewModel() {

    val wordInput = MutableLiveData("")
    val descriptionInput = MutableLiveData("")

    val termList = termRepository.findAll()

    private val _showedText = MutableLiveData("")
    val showedText: LiveData<String> get() = _showedText

    fun onSubmit() {
        viewModelScope.launch {
            if(!wordInput.value.isNullOrEmpty() && !descriptionInput.value.isNullOrEmpty()){
                val newTerm = Term(wordInput.value!!, descriptionInput.value!!)
                termRepository.insertOrReplace(newTerm)
                wordInput.value = ""
                descriptionInput.value = ""
            }
        }
    }

    fun updateShowedText(terms: List<Term>){
        var newtext = ""
        terms.forEach{
            newtext += "Termino: ${it.word}\n\t\t\tDesc: ${it.description}\n\n"
        }

        _showedText.value = newtext
    }

}