package com.demo.qagency.presentation.comments.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.demo.qagency.R
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.presentation.ui.theme.SpaceMedium
import com.demo.qagency.presentation.ui.theme.SpaceSmall

@Composable
fun CommentItem(
        comment: Comment,
        onCommentClick: () -> Unit = {},
){
    Box(
            modifier = Modifier
                    .fillMaxWidth()
    ) {
            Column(
                    modifier = Modifier
                            .fillMaxWidth()
            ) {
                ActionRow(
                        username = comment.name,
                        modifier = Modifier.fillMaxWidth(),
                        onCommentClick = onCommentClick,
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(SpaceSmall))
                Text(
                        text = buildAnnotatedString {
                            append(comment.body)
                        },
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = androidx.compose.ui.Modifier.height(SpaceMedium))
            }
    }
}


@Composable
fun EngagementButtons(
        modifier: Modifier = Modifier,
        iconSize: Dp = 30.dp,
        onCommentClick: () -> Unit = {},
) {
    Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
    ) {
        IconButton(
                onClick = {
                    onCommentClick()
                },
                modifier = Modifier.size(iconSize)
        ) {
            Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = stringResource(id = R.string.comment)
            )
        }
    }
}

@Composable
fun ActionRow(
        modifier: Modifier = Modifier,
        isLiked: Boolean = false,
        onLikeClick: () -> Unit = {},
        onCommentClick: () -> Unit = {},
        onShareClick: () -> Unit = {},
        username: String,
        onUsernameClick: () -> Unit = {}
) {
    Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier,
    ) {
        Text(
                text = username,
                style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                        .clickable {
                            onUsernameClick()
                        }
        )
        EngagementButtons(
                onCommentClick = onCommentClick
        )
    }
}