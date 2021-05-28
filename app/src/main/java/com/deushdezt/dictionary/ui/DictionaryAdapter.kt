package com.deushdezt.dictionary.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deushdezt.dictionary.R
import com.deushdezt.dictionary.data.entity.Term

class DictionaryAdapter(private val deleteHandler: (Term) -> Unit): RecyclerView.Adapter<DictionaryAdapter.TermViewHolder>() {

    private var dictionary: List<Term>? = null

    class TermViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(term: Term, deleteHandler: (Term) -> Unit) {
            itemView.apply {
                findViewById<TextView>(R.id.title_item).text = term.word
                findViewById<TextView>(R.id.description_item).text = term.description
                findViewById<ImageView>(R.id.delete_item_action).setOnClickListener {
                    deleteHandler(term)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TermViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.term_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TermViewHolder, position: Int) {
        dictionary?.let {
            val term = it[position]
            holder.bind(term, deleteHandler)
        }
    }

    override fun getItemCount() = dictionary?.size ?: 0

    fun setData(terms: List<Term>){
        dictionary = terms
        notifyDataSetChanged()
    }
}