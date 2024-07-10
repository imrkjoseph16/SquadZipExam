package com.imrkjoseph.squadzipexam.app.shared.extension

import android.view.View

fun View.setVisible(canShow: Boolean) {
    this.visibility = if (canShow) View.VISIBLE else View.GONE
}