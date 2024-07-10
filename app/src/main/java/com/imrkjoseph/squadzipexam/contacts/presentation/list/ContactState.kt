package com.imrkjoseph.squadzipexam.contacts.presentation.list

import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactDetailsResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse

open class ContactState

object ShowContactListNoData : ContactState()

object ShowContactLoading : ContactState()

object ShowContactDismissLoading : ContactState()

data class GetContactList(val response: ContactListResponse) : ContactState()

data class GetContactDetails(val response: ContactDetailsResponse) : ContactState()

data class GetUiItems(val uiItems: List<Any>) : ContactState()

data class ShowContactError(val throwable: Throwable) : ContactState()