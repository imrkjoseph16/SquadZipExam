package com.imrkjoseph.squadzipexam.app.shared.extension

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.text.toSpannable
import java.util.Locale

fun View.setVisible(canShow: Boolean) {
    this.visibility = if (canShow) View.VISIBLE else View.GONE
}

fun String.highlightText(highlightText: String): Spannable {
    if (this.lowercase(Locale.getDefault()).contains(highlightText.lowercase(Locale.getDefault())).not()) return this.toSpannable()

    val startPos = this.lowercase(Locale.getDefault()).indexOf(highlightText.lowercase(Locale.getDefault()))
    val endPos = startPos + highlightText.length

    val span: Spannable = SpannableString(this)
    span.setSpan(ForegroundColorSpan(Color.RED), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

    return span
}