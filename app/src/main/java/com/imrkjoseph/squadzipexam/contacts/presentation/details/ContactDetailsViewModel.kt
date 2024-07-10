package com.imrkjoseph.squadzipexam.contacts.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.squadzipexam.app.shared.extension.coRunCatching
import com.imrkjoseph.squadzipexam.contacts.domain.ContactUseCase
import com.imrkjoseph.squadzipexam.contacts.presentation.list.ContactState
import com.imrkjoseph.squadzipexam.contacts.presentation.list.GetContactDetails
import com.imrkjoseph.squadzipexam.contacts.presentation.list.ShowContactError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val contactUseCase: ContactUseCase
) : ViewModel() {

    private val args = ContactDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _uiState = MutableStateFlow(value = ContactState())

    val uiState = _uiState.asStateFlow()

    init {
        getContactDetails()
    }

    private fun getContactDetails() {
        viewModelScope.launch {
            coRunCatching {
                contactUseCase.getContactDetails(contactId = args.contactId)
            }.onSuccess { response ->
                updateUiState(state = GetContactDetails(response = response))
            }.onFailure {  error ->
                updateUiState(state = ShowContactError(throwable = error))
            }
        }
    }

    private fun updateUiState(state: ContactState) {
        _uiState.value = state
    }
}