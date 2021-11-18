package com.demo.qagency.presentation.comments.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
        onCommentClick: (Comment) -> Unit = {},
){
    Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
    ) {
            Row(
                    modifier = Modifier.fillMaxWidth()
                        .clickable { onCommentClick(comment) }
            ) {

                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                        text = buildAnnotatedString {
                            append(comment.body)
                        },
                        style = MaterialTheme.typography.body2,
                        overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
            }
    }
}
