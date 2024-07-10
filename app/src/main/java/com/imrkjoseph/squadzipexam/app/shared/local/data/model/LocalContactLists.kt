package com.imrkjoseph.squadzipexam.app.shared.local.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.imrkjoseph.squadzipexam.app.util.Default.Companion.DB_CONTACT_LIST
import com.imrkjoseph.squadzipexam.app.util.LocalConverter

@Entity(tableName = DB_CONTACT_LIST)
data class LocalContactDetails(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("data")
    @TypeConverters(LocalConverter::class)
    val data: List<LocalData> = emptyList(),

    @ColumnInfo("total")
    val total: Int? = null,

    @ColumnInfo("page")
    val page: Int? = null,

    @ColumnInfo("totalPages")
    val totalPages: Int? = null
)

data class LocalData(
    @ColumnInfo("id")
    val id: Int,

    @ColumnInfo("avatar")
    val avatar: String? = null,

    @ColumnInfo("email")
    val email: String? = null,

    @ColumnInfo("lastName")
    val lastName: String? = null,

    @ColumnInfo("firstName")
    val firstName: String? = null
)
