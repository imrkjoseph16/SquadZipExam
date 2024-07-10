package com.imrkjoseph.squadzipexam.app.shared.local.data.repository

import com.imrkjoseph.squadzipexam.app.shared.local.data.transformer.LocalTransformer
import com.imrkjoseph.squadzipexam.app.shared.local.domain.DatabaseService
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import javax.inject.Inject

class LocalDatabaseRepository @Inject constructor(
    databaseService: DatabaseService,
    private val localTransformer: LocalTransformer
) {

    private val localClient = databaseService.contactsDao()

    suspend fun getContactList() =  localTransformer.transformLocalToResponse(result = localClient.getContactList())

    suspend fun getContactDetails(contactId: Int) = localTransformer.transformLocalDetails(
        contactId = contactId,
        result =  localClient.getContactList()
    )

    suspend fun saveLocalContactLists(response: ContactListResponse?) = localClient.saveLocalContactLists(
        contactListDetails = localTransformer.transformResponseToLocal(response = response)
    )
}