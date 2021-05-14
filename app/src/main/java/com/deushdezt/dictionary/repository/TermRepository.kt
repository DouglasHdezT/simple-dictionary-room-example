package com.deushdezt.dictionary.repository

import com.deushdezt.dictionary.data.dao.TermDao
import com.deushdezt.dictionary.data.entity.Term

class TermRepository(private val termDao: TermDao) {
    suspend fun insertOrReplace(term: Term) = termDao.insertOrUpdate(term)
    fun findAll() = termDao.findAll()
    suspend fun delete(term: Term) = termDao.delete(term)
    suspend fun search(query: String) = termDao.search(query)
 }