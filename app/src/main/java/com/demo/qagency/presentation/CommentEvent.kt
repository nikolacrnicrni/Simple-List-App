package com.demo.qagency.presentation

sealed class CommentEvent {

    object DismissLogoutDialog : CommentEvent()

    object ShowLogoutDialog : CommentEvent()
}
