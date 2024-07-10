package com.imrkjoseph.squadzipexam.contacts.data.client

import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactDetailsResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactApiClient {

    @GET("api/users")
    suspend fun getContactList() : ContactListResponse

    @GET("api/users/{id}")
    suspend fun getContactDetails(@Path("id") contactId: Int): ContactDetailsResponse
}