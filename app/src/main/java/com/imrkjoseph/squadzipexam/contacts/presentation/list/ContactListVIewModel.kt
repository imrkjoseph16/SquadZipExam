package com.imrkjoseph.squadzipexam.contacts.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.squadzipexam.app.shared.extension.coRunCatching
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import com.imrkjoseph.squadzipexam.contacts.domain.ContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListVIewModel @Inject constructor(
    private val contactUseCase: ContactUseCase,
    private val factory: ContactListFactory
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = ContactState())

    val uiState = _uiState.asStateFlow()

    init {
        getContactList()
    }

    private fun getContactList() {
        updateUiState(state = ShowContactLoading)

        viewModelScope.launch {
            coRunCatching {
                contactUseCase.getContactList()
            }.onSuccess { response ->
                handleContactListResult(result = response)
            }.onFailure { error ->
                updateUiState(state = ShowContactError(throwable = error))
            }
        }
    }

    private fun handleContactListResult(result: ContactListResponse) {
        updateUiState(
            state = if (result.data.isEmpty()) ShowContactListNoData
            else GetContactList(response = result)
        )
    }

    fun getUiItems(response: ContactListResponse) {
        factory.createOverview(data = response).also { uiItems ->
            updateUiState(state = GetUiItems(uiItems = uiItems))
        }
    }

    private fun updateUiState(state: ContactState) {
        _uiState.value = state
    }
}