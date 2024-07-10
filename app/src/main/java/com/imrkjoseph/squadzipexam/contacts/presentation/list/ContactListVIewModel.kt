package com.imrkjoseph.squadzipexam.contacts.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrkjoseph.squadzipexam.app.shared.extension.coRunCatching
import com.imrkjoseph.squadzipexam.app.shared.local.domain.DatabaseUseCase
import com.imrkjoseph.squadzipexam.contacts.data.dto.ContactListResponse
import com.imrkjoseph.squadzipexam.contacts.domain.ContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListVIewModel @Inject constructor(
    private val contactUseCase: ContactUseCase,
    private val factory: ContactListFactory,
    private val databaseUseCase: DatabaseUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = ContactState())

    val uiState = _uiState.asStateFlow()

    init {
        getLocalContactLists()
    }

    private fun getLocalContactLists() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val cachedList = databaseUseCase.getContactLists()
            // Check if the cache details is not empty,
            // it means the response is already saved in local database.
            if (cachedList.data.isNotEmpty()) handleContactListResult(result = cachedList)
            else getContactList()
        }
    }

    private fun getContactList() {
        updateUiState(state = ShowContactLoading)

        viewModelScope.launch {
            coRunCatching {
                contactUseCase.getContactList()
            }.onSuccess { response ->
                handleContactListResult(result = response)

                // Saved to local database to cached the response.
                saveContactListsToLocal(response = response)
            }.onFailure { error ->
                updateUiState(state = ShowContactError(throwable = error))
            }
        }
    }

    private fun saveContactListsToLocal(response: ContactListResponse?) {
        viewModelScope.launch(context = Dispatchers.IO) {
            databaseUseCase.saveLocalContactLists(response = response)
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