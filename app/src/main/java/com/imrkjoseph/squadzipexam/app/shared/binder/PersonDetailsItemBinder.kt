package com.imrkjoseph.squadzipexam.app.shared.binder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.imrkjoseph.squadzipexam.app.component.RecyclerViewHolder
import com.imrkjoseph.squadzipexam.app.shared.dto.ListItemViewDto
import com.imrkjoseph.squadzipexam.databinding.SharedListItemBinding

data class ContactListItem(
    val id: Any? = null,
    val dto: ListItemViewDto
)

fun <T : Any>setupContactListItemBinder(
    dtoRetriever: (T) -> ListItemViewDto,
    onItemClicked: (ListItemViewDto) -> Unit = {}
) = object : RecyclerViewHolder<SharedListItemBinding, T> {
    override fun bind(binder: SharedListItemBinding, item: T) {
        with(binder) {
            val itemDto = dtoRetriever(item)
            dto = itemDto
            root.setOnClickListener { onItemClicked.invoke(itemDto) }
            executePendingBindings()
        }
    }

    override fun inflate(parent: ViewGroup) = SharedListItemBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)
}