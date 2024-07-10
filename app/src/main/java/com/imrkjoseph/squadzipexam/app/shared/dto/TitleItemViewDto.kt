package com.imrkjoseph.squadzipexam.app.shared.dto

import com.imrkjoseph.squadzipexam.app.component.TextLine

/**
 * Layout rendered in [com.imrkjoseph.squadzipexam.R.layout.shared_title_item]
 * */
data class TitleItemViewDto(
    val title: TextLine = TextLine.EMPTY,
    val textSize: Float? = 18F
)