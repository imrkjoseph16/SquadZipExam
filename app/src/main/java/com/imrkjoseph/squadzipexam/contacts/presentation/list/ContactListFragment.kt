package com.imrkjoseph.squadzipexam.contacts.presentation.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imrkjoseph.squadzipexam.app.component.CustomRecyclerView
import com.imrkjoseph.squadzipexam.app.foundation.BaseFragment
import com.imrkjoseph.squadzipexam.app.shared.binder.ContactListItem
import com.imrkjoseph.squadzipexam.app.shared.binder.EmptyItemViewDtoBinder
import com.imrkjoseph.squadzipexam.app.shared.binder.SpaceItemViewDtoBinder
import com.imrkjoseph.squadzipexam.app.shared.binder.setupContactListItemBinder
import com.imrkjoseph.squadzipexam.app.shared.binder.setupSearchItemBinder
import com.imrkjoseph.squadzipexam.app.shared.binder.setupTitleItemBinder
import com.imrkjoseph.squadzipexam.app.shared.extension.setVisible
import com.imrkjoseph.squadzipexam.databinding.FragmentContactListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactListFragment : BaseFragment<FragmentContactListBinding>(FragmentContactListBinding::inflate) {

    private val viewModel: ContactListVIewModel by viewModels()

    override fun onViewCreated() {
        super.onViewCreated()
        with(binding) {
            setupComponents()
            setupObserver()
        }
    }

    private fun FragmentContactListBinding.setupComponents() {
        contactList.setupContactList()
    }

    private fun CustomRecyclerView.setupContactList() {
        addItemBindings(viewHolders = SpaceItemViewDtoBinder)
        addItemBindings(viewHolders = EmptyItemViewDtoBinder)
        addItemBindings(viewHolders = setupTitleItemBinder())
        addItemBindings(viewHolders = setupSearchItemBinder(
            onSearchKey = { query ->
                viewModel.searchContacts(searchKey = query)
            }
        ))
        addItemBindings(viewHolders = setupContactListItemBinder(
            dtoRetriever = ContactListItem::dto,
            onItemClicked = { dto ->
                navigateToDetailScreen(dto.itemId)
            }
        ))
    }

    private fun setupObserver() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                uiState.collectLatest {  state ->
                    when(state) {
                        is ShowContactLoading -> updateLoadingState(isShow = true)
                        is ShowContactDismissLoading, ShowContactListNoData,
                        is ShowContactError -> updateLoadingState(isShow = false)
                        is GetContactList -> getUiItems(response = state.response).also { updateLoadingState(isShow = false) }
                        is GetUiItems -> binding.contactList.setItems(items = state.uiItems)
                    }
                }
            }
        }
    }

    private fun updateLoadingState(isShow: Boolean) = binding.widgetLoading.root.setVisible(canShow = isShow)

    private fun navigateToDetailScreen(contactId: Int) = findNavController().navigate(directions = ContactListFragmentDirections.actionToContactDetailsScreen(contactId))
}