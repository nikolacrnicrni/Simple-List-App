package com.demo.qagency.util

sealed class Screen(val route: String) {
    object CommentListScreen : Screen("comment_list_screen")
}
