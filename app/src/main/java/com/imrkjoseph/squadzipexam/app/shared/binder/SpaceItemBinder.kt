package com.imrkjoseph.squadzipexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.squadzipexam.app.component.RecyclerViewHolder
import com.imrkjoseph.squadzipexam.app.shared.dto.SpaceItemViewDto
import com.imrkjoseph.squadzipexam.databinding.SharedSpaceListItemBinding

val SpaceItemViewDtoBinder = object :
    RecyclerViewHolder<SharedSpaceListItemBinding, SpaceItemViewDto> {
    override fun bind(binder: SharedSpaceListItemBinding, item: SpaceItemViewDto) {
        with(binder) {
            data = item
            executePendingBindings()
        }
    }

    override fun inflate(parent: ViewGroup) = SharedSpaceListItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}