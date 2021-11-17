package com.demo.qagency.presentation.comments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.qagency.presentation.CommentViewModel
import com.demo.qagency.presentation.comments.components.CommentItem
import com.demo.qagency.util.Screen

@Composable
fun CommentsListScreen(
        onNavigate: (String) -> Unit = {},
        viewModel: CommentViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value
    val lazyListState = rememberLazyListState()


    Box(
        modifier = Modifier
                .fillMaxSize()
    ) {
        LazyColumn(
                modifier = Modifier
                        .fillMaxSize(),
                state = lazyListState
        ) {
            items(pagingState.items.size) { i ->
                val comment = pagingState.items[i]
                if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                    viewModel.loadNextPosts()
                }

                CommentItem(
                        comment = comment,
                        onCommentClick = {

                        },
                )
            }
            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }

    }

}