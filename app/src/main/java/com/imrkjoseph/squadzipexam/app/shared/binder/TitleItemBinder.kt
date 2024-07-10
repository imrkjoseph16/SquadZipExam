package com.imrkjoseph.squadzipexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.squadzipexam.app.component.RecyclerViewHolder
import com.imrkjoseph.squadzipexam.app.shared.dto.TitleItemViewDto
import com.imrkjoseph.squadzipexam.databinding.SharedTitleItemBinding

fun setupTitleItemBinder() = object : RecyclerViewHolder<SharedTitleItemBinding, TitleItemViewDto> {
    override fun bind(binder: SharedTitleItemBinding, item: TitleItemViewDto) = with(binder) {
        data = item
        executePendingBindings()
    }

    override fun inflate(parent: ViewGroup) = SharedTitleItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}