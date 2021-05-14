package com.deushdezt.dictionary.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "term_table")
data class Term(
    @PrimaryKey
    var word: String,
    var description: String
)
