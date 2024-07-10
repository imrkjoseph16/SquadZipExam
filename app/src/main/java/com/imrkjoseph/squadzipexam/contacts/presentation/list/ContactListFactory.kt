package com.imrkjoseph.squadzipexam.contacts.presentation.list

import androidx.annotation.StringRes
import com.imrkjoseph.squadzipexam.R
import com.imrkjoseph.squadzipexam.app.component.TextLine
import com.imrkjoseph.squadzipexam.app.shared.binder.ContactListItem
import com.imrkjoseph.squadzipexam.app.shared.dto.EmptyItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.ListItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.Data
import javax.inject.Inject

class ContactListFactory @Inject constructor() {

    fun createOverview(data: ContactListResponse) = data.prepareList()

    private fun ContactListResponse.prepareList() = buildList {
        // Title
        add(element = SpaceItemViewDto(R.dimen.distance_20x))
        add(element = setupSectionTitle(textRes = R.string.title_contacts, textSize = 32F))
        add(element = SpaceItemViewDto(R.dimen.distance_16x))

        // Contact List
        add(element = SpaceItemViewDto(R.dimen.distance_16x))
        setupContactList(data = data)
    }

    private fun MutableList<Any>.setupContactList(data: List<Data>?) {
        if (data?.isEmpty() == true) add(element = EmptyItemViewDto(textRes = R.string.title_empty_contacts))
        else data?.map { details ->
            // Details
            add(element = ContactListItem(
                id = details.id,
                dto = ListItemViewDto(
                    firstLine = "${details.firstName} ${details.lastName}",
                    secondLine = details.email,
                    avatarUrl = details.avatar,
                    itemId = details.id
                )
            ))
        }
    }

    private fun setupSectionTitle(
        @StringRes textRes: Int? = null,
        text: String? = null,
        textSize: Float? = null
    ) = TitleItemViewDto(
        title = TextLine(textRes = textRes, text = text),
        textSize = textSize
    )
}