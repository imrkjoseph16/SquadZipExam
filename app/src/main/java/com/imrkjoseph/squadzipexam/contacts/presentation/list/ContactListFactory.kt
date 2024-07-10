package com.imrkjoseph.squadzipexam.contacts.presentation.list

import androidx.annotation.StringRes
import com.imrkjoseph.squadzipexam.R
import com.imrkjoseph.squadzipexam.app.component.TextLine
import com.imrkjoseph.squadzipexam.app.shared.binder.ContactListItem
import com.imrkjoseph.squadzipexam.app.shared.dto.EmptyItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.ListItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.SearchItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.squadzipexam.app.shared.extension.highlightText
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import com.imrkjoseph.squadzipexam.contacts.data.dto.Data
import javax.inject.Inject

class ContactListFactory @Inject constructor() {

    fun createOverview(data: ContactListResponse, searchKey: String) = data.prepareList(searchKey)

    private fun ContactListResponse.prepareList(searchKey: String) = buildList {
        // Title
        add(element = SpaceItemViewDto(R.dimen.distance_20x))
        add(element = setupSectionTitle(textRes = R.string.title_contacts, textSize = 32F))

        // Search Widget
        add(element = SpaceItemViewDto(R.dimen.distance_16x))
        add(element = SearchItemViewDto())

        // Contact List
        add(element = SpaceItemViewDto(R.dimen.distance_16x))
        setupContactList(data = data, searchKey = searchKey)
    }

    private fun MutableList<Any>.setupContactList(data: List<Data>?, searchKey: String) {
        if (data?.isEmpty() == true) add(element = EmptyItemViewDto(textRes = R.string.title_empty_contacts))
        else data?.sortedBy { it.firstName }?.map { details ->
            // Alphabetical Letter
            setupAlphabeticalLetters(details.firstName?.first().toString())

            // Details
            add(element = ContactListItem(
                id = details.id,
                dto = ListItemViewDto(
                    spanString = "${details.firstName} ${details.lastName}".highlightText(highlightText = searchKey),
                    secondLine = details.email,
                    avatarUrl = details.avatar,
                    itemId = details.id
                )
            ))
        }
    }

    private fun MutableList<Any>.setupAlphabeticalLetters(letter: String) {
        val alphabeticalOrderItem = setupSectionTitle(text = letter, textSize = 18F)
        if (this.contains(alphabeticalOrderItem).not()) add(element = alphabeticalOrderItem)
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