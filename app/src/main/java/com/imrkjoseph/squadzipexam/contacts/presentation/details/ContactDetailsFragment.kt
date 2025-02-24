package com.imrkjoseph.squadzipexam.contacts.presentation.details

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.imrkjoseph.squadzipexam.app.foundation.BaseFragment
import com.imrkjoseph.squadzipexam.contacts.presentation.list.GetContactDetails
import com.imrkjoseph.squadzipexam.databinding.FragmentContactDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ContactDetailsFragment : BaseFragment<FragmentContactDetailsBinding>(bindingInflater = FragmentContactDetailsBinding::inflate) {

    private val viewModel: ContactDetailsViewModel by viewModels()

    override fun onViewCreated() {
        super.onViewCreated()
        with(binding) {
            setupComponents()
            setupObserver()
        }
    }

    private fun FragmentContactDetailsBinding.setupComponents() {
        arrowBack.setOnClickListener {
            goBackToPreviousScreen()
        }
    }

    private fun setupObserver() {
        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                uiState.collectLatest { state ->
                    when(state) {
                        is GetContactDetails -> binding.details = state.response
                        else -> { }
                    }
                }
            }
        }
    }

    private fun goBackToPreviousScreen() = findNavController().popBackStack()
}