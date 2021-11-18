package com.demo.qagency.presentation.comments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.qagency.R
import com.demo.qagency.presentation.CommentViewModel
import com.demo.qagency.presentation.comments.components.CommentItem
import com.demo.qagency.presentation.ui.theme.MarginSizeSmall
import com.demo.qagency.presentation.ui.theme.SpaceMedium
import com.demo.qagency.presentation.ui.theme.SpaceSmall
import com.demo.qagency.presentation.util.UiEvent
import com.demo.qagency.presentation.util.asString
import com.demo.qagency.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommentsListScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    viewModel: CommentViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value
    val lazyListState = rememberLazyListState()


    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.pagingState
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }

    }

    Box(modifier = Modifier.fillMaxSize())
    {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MarginSizeSmall)
        ) {
            Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.h3 )
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

                    Spacer(modifier = Modifier.height(SpaceSmall))
                }
            }
        }
        if (pagingState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }

    }

}