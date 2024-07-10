package com.imrkjoseph.squadzipexam.contacts.domain

import com.imrkjoseph.squadzipexam.contacts.data.repository.ContactRepository
import javax.inject.Inject

class ContactUseCase @Inject constructor(
    private var contactRepository: ContactRepository
) {

    suspend fun getContactList() = contactRepository.getContactList()

    suspend fun getContactDetails(contactId: Int) = contactRepository.getContactDetails(contactId = contactId)
}