package com.demo.qagency.presentation.util

import android.content.Context
import com.demo.qagency.util.UiText

fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.DynamicString -> this.value
        is UiText.StringResource -> context.getString(this.id)
    }
}
