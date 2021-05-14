package com.deushdezt.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.deushdezt.dictionary.databinding.ActivityMainBinding
import com.deushdezt.dictionary.ui.DictionaryViewModel
import com.deushdezt.dictionary.ui.DictionaryViewModelFactory

class MainActivity : AppCompatActivity() {

    private val dictApp by lazy {
        application as DictionaryApplication
    }

    private val dictionaryViewModelFactory: DictionaryViewModelFactory by lazy {
        DictionaryViewModelFactory(dictApp.termRepository)
    }

    private val dictionaryViewModel: DictionaryViewModel by viewModels{
        dictionaryViewModelFactory
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewmodel = dictionaryViewModel
            lifecycleOwner = this@MainActivity
        }

        dictionaryViewModel.termList.observe(this) {
            dictionaryViewModel.updateShowedText(it)
        }
    }
}