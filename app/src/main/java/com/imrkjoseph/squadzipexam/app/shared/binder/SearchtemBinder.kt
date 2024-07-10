package com.imrkjoseph.squadzipexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.imrkjoseph.squadzipexam.app.component.RecyclerViewHolder
import com.imrkjoseph.squadzipexam.app.shared.dto.SearchItemViewDto
import com.imrkjoseph.squadzipexam.databinding.SharedSearchItemBinding

fun setupSearchItemBinder(
    onSearchKey: (String) -> Unit = {}
) = object : RecyclerViewHolder<SharedSearchItemBinding, SearchItemViewDto> {
    override fun bind(binder: SharedSearchItemBinding, item: SearchItemViewDto) = with(binder) {
        data = item
        searchView.doAfterTextChanged { onSearchKey.invoke(it.toString()) }
        executePendingBindings()
    }

    override fun inflate(parent: ViewGroup) = SharedSearchItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}