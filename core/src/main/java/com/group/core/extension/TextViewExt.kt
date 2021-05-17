package com.group.core.extension

import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat

fun AppCompatTextView.setTextCompute(text: CharSequence) {
    setTextFuture(PrecomputedTextCompat.getTextFuture(text, textMetricsParamsCompat, null))
}
