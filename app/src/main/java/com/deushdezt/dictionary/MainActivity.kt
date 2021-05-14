package com.deushdezt.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.deushdezt.dictionary.databinding.ActivityMainBinding
import com.deushdezt.dictionary.repository.TermRepository
import com.deushdezt.dictionary.ui.DictionaryViewModel
import com.deushdezt.dictionary.ui.DictionaryViewModelFactory

class MainActivity : AppCompatActivity() {

    private val dictionaryApplication by lazy {
        application as DictionaryApplication
    }

    private val factory: DictionaryViewModelFactory by lazy {
        val repository = dictionaryApplication.termRepository
        DictionaryViewModelFactory(repository)
    }

    private val dictionaryViewModel: DictionaryViewModel by viewModels {
        factory
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}