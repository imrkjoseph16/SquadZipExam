package com.imrkjoseph.squadzipexam.app.shared.view

import android.text.Spannable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.core.text.toSpanned
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import coil.load
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("visible")
fun setVisible(view: View, visible: Boolean) {
    view.isVisible = visible
}

@BindingAdapter("loadUrl")
fun loadUrl(view: CircleImageView, url: String?) {
    url?.let { it ->
        view.load(it)
    }
}

@BindingAdapter("setSpanText")
fun setSpanText(view: TextView, text: Spannable) {
    view.text = text
}

@BindingAdapter("setTextSize")
fun setTextSize(view: TextView, textSize: Float) {
    view.textSize = textSize
}

@BindingAdapter("setCustomHeight")
fun setCustomHeight(view: View, @DimenRes margin: Int) {
    view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
        height = view.resources.getDimension(margin).toInt()
    }
}