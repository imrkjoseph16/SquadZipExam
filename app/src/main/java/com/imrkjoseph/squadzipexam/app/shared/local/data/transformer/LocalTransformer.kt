package com.imrkjoseph.squadzipexam.app.shared.local.data.transformer

import com.imrkjoseph.squadzipexam.app.shared.local.data.model.LocalContactDetails
import com.imrkjoseph.squadzipexam.app.shared.local.data.model.LocalData
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactDetailsResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.Data
import javax.inject.Inject

class LocalTransformer @Inject constructor() {

    fun transformLocalToResponse(result: LocalContactDetails?) = ContactListResponse(
        data = result?.data?.map { localData ->
            Data(
                id = localData.id,
                firstName = localData.firstName,
                lastName = localData.lastName,
                email = localData.email,
                avatar = localData.avatar
            )
        } ?: emptyList(),
        page = result?.page,
        total = result?.total,
        totalPages = result?.totalPages
    )

    fun transformLocalDetails(
        contactId: Int,
        result: LocalContactDetails?
    ): ContactDetailsResponse {
        val localDetails = result?.data?.first { it.id == contactId }
        return localDetails?.id?.let {
            ContactDetailsResponse(
                data = Data(
                    id = localDetails.id,
                    firstName = localDetails.firstName,
                    lastName = localDetails.lastName,
                    email = localDetails.email,
                    avatar = localDetails.avatar
                )
            )
        } ?: ContactDetailsResponse()
    }

    fun transformResponseToLocal(response: ContactListResponse?) = LocalContactDetails(
        data = response?.data?.map { details ->
            LocalData(
                id = details.id,
                firstName = details.firstName,
                lastName = details.lastName,
                email = details.email,
                avatar = details.avatar
            )
        } ?: emptyList(),
        page = response?.page,
        total = response?.total,
        totalPages = response?.totalPages,
    )
}