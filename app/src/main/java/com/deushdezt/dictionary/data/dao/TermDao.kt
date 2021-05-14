package com.deushdezt.dictionary.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.deushdezt.dictionary.data.entity.Term

@Dao
interface TermDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(term: Term)

    @Query("SELECT * FROM term_table")
    fun findAll(): LiveData<List<Term>>

    @Query("SELECT * from term_table WHERE word = :query")
    suspend fun search(query: String): Term

    @Delete
    suspend fun delete(term: Term)
}