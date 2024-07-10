package com.imrkjoseph.squadzipexam.contacts.data.repository

import com.imrkjoseph.squadzipexam.contacts.data.client.ContactApiClient
import dagger.Lazy
import retrofit2.Retrofit
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val retrofit: Lazy<Retrofit>
) {

    private val apiClient: ContactApiClient by lazy { retrofit.get().create(ContactApiClient::class.java) }

    suspend fun getContactList() = apiClient.getContactList()

    suspend fun getContactDetails(contactId: Int) = apiClient.getContactDetails(contactId = contactId)
}