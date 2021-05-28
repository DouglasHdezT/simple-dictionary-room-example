package com.deushdezt.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.deushdezt.dictionary.databinding.ActivityMainBinding
import com.deushdezt.dictionary.ui.DictionaryAdapter
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

        val dictionaryAdapter = DictionaryAdapter {
            dictionaryViewModel.onDeleteTerm(it)
        }

        binding.apply {
            viewmodel = dictionaryViewModel
            lifecycleOwner = this@MainActivity
        }

        findViewById<RecyclerView>(R.id.terms_rv).apply {
            adapter = dictionaryAdapter
        }

        dictionaryViewModel.termList.observe(this) {
            dictionaryAdapter.setData(it)
        }
    }
}