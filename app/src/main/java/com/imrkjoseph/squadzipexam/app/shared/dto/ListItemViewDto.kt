package com.imrkjoseph.squadzipexam.app.shared.dto

/**
 * Reusable component
 *
 * Describes data rendered in [com.imrkjoseph.squadzipexam.R.layout.shared_list_item]
 * */
data class ListItemViewDto(
    val itemId: Int,
    val firstLine: String? = null,
    val secondLine: String? = null,
    val avatarUrl: String? = null
)