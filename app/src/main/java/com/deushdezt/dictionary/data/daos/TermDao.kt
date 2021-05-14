package com.deushdezt.dictionary.data.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.deushdezt.dictionary.data.entities.Term

@Dao
interface TermDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(term: Term)

    @Query("SELECT * FROM term_table")
    fun findAll(): LiveData<List<Term>>

    @Query("SELECT * from term_table WHERE word = :word")
    suspend fun search(word: String): Term?

    @Delete
    suspend fun delete(term: Term)
}