package com.deushdezt.dictionary.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deushdezt.dictionary.data.daos.TermDao
import com.deushdezt.dictionary.data.entities.Term

@Database(entities = [Term::class], version = 1)
abstract class DictionaryDatabase: RoomDatabase() {

    abstract fun termDao(): TermDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context,
                DictionaryDatabase::class.java, "dictionary_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }

}