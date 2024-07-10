package com.imrkjoseph.squadzipexam.app.shared.local.domain

import com.imrkjoseph.squadzipexam.app.shared.local.data.repository.LocalDatabaseRepository
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import javax.inject.Inject

class DatabaseUseCase @Inject constructor(
    private val localRepository: LocalDatabaseRepository
) {
    suspend fun getContactLists() = localRepository.getContactList()

    suspend fun getContactDetails(contactId: Int) = localRepository.getContactDetails(contactId = contactId)

    suspend fun saveLocalContactLists(response: ContactListResponse?) = localRepository.saveLocalContactLists(response)
}