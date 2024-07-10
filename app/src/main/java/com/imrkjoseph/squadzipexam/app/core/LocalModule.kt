package com.imrkjoseph.squadzipexam.app.core

import android.content.Context
import com.imrkjoseph.squadzipexam.app.shared.local.domain.DatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalModule {

    @Singleton
    @Provides
    fun executeRoomInstance(
        @ApplicationContext
        context: Context,
    ) = DatabaseService.getInstance(context)

    @Singleton
    @Provides
    fun executeDatabaseDao(
        databaseService: DatabaseService
    ) = databaseService.contactsDao()
}