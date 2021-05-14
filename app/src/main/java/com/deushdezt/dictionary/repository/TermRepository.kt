package com.deushdezt.dictionary.repository

import com.deushdezt.dictionary.data.daos.TermDao
import com.deushdezt.dictionary.data.entities.Term

class TermRepository(private val termDao: TermDao) {
    suspend fun insertOrUpdate(term: Term) = termDao.insertOrUpdate(term)
    suspend fun search(word: String) = termDao.search(word)
    suspend fun delete(term: Term) = termDao.delete(term)
    fun findAll() = termDao.findAll()
}