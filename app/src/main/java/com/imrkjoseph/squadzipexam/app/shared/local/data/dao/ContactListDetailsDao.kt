package com.imrkjoseph.squadzipexam.app.shared.local.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imrkjoseph.squadzipexam.app.shared.local.data.model.LocalContactDetails
import com.imrkjoseph.squadzipexam.app.util.Default.Companion.DB_CONTACT_LIST

@Dao
interface ContactListDetailsDao {

    @Query("Select * from $DB_CONTACT_LIST")
    suspend fun getContactList() : LocalContactDetails?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveLocalContactLists(contactListDetails: LocalContactDetails) : Long
}