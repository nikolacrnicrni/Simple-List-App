package com.demo.qagency.presentation.util

import com.demo.qagency.util.Event
import com.demo.qagency.util.UiText

sealed class UiEvent: Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
}