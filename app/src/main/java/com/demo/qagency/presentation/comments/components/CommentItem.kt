package com.demo.qagency.presentation.comments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.qagency.R
import com.demo.qagency.domain.models.Comment
import com.demo.qagency.presentation.ui.theme.*

@Composable
fun CommentItem(
        comment: Comment,
        onCommentClick: (Comment) -> Unit = {},
){
    Card(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            elevation = ElevationDimen,
    ) {
            Column(
                Modifier.clickable { onCommentClick(comment) }
            ) {
                TextWithImage(
                    content = comment.email,
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email"
                )
                TextWithImage(
                    content = comment.name,
                    imageVector = Icons.Default.ShortText,
                    contentDescription = "Title"
                )
                TextWithImage(
                    content = comment.body,
                    imageVector = Icons.Default.ContentPaste,
                    contentDescription = "Content"
                )
            }
    }
}


@Composable
fun TextWithImage(
    content: String,
    imageVector: ImageVector,
    contentDescription: String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = PrimaryBlue,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Icon(imageVector = imageVector, tint = Color.White, modifier = Modifier.padding(5.dp),  contentDescription = contentDescription)
        }

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = content,
            style = TextStyle(
                color = Dark,
                fontSize = 16.sp,
                fontFamily = fontFamily
            )
        )

    }
}