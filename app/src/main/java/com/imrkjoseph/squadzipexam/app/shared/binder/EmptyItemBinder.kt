package com.imrkjoseph.squadzipexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.squadzipexam.app.component.RecyclerViewHolder
import com.imrkjoseph.squadzipexam.app.shared.dto.EmptyItemViewDto
import com.imrkjoseph.squadzipexam.databinding.SharedEmptyListItemBinding

val EmptyItemViewDtoBinder = object :
    RecyclerViewHolder<SharedEmptyListItemBinding, EmptyItemViewDto> {
    override fun bind(binder: SharedEmptyListItemBinding, item: EmptyItemViewDto) {
        with(binder) {
            data = item
            executePendingBindings()
        }
    }

    override fun inflate(parent: ViewGroup) = SharedEmptyListItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}