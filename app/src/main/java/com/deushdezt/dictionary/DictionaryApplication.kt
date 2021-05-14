package com.deushdezt.dictionary

import android.app.Application
import com.deushdezt.dictionary.data.DictionaryDatabase
import com.deushdezt.dictionary.repository.TermRepository

class DictionaryApplication: Application() {
    private val database by lazy {
        DictionaryDatabase.getDatabase(this)
    }

    val termRepository by lazy {
        val termDao = database.termDao()
        TermRepository(termDao)
    }
}