package com.imrkjoseph.squadzipexam.app.shared.local.domain

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imrkjoseph.squadzipexam.app.shared.local.data.dao.ContactListDetailsDao
import com.imrkjoseph.squadzipexam.app.shared.local.data.model.LocalContactDetails
import com.imrkjoseph.squadzipexam.app.util.Default.Companion.DB_CONTACTS_PATH
import com.imrkjoseph.squadzipexam.app.util.LocalConverter

@Database(entities = [LocalContactDetails::class], version = 1)
@TypeConverters(LocalConverter::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun contactsDao() : ContactListDetailsDao

    companion object {
        @Volatile
        private var DB_INSTANCE: DatabaseService? = null

        fun getInstance(context: Context) = DB_INSTANCE ?: synchronized(this) {
            DB_INSTANCE ?: buildDatabase(context).also { DB_INSTANCE = it }
        }

        private fun buildDatabase(context: Context): DatabaseService {
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseService::class.java,
                    DB_CONTACTS_PATH
                ).allowMainThreadQueries().build()
                DB_INSTANCE = instance
                return instance
            }
        }
    }
}