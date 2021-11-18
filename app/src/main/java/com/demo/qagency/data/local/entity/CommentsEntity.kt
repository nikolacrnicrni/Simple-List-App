package com.demo.qagency.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.qagency.domain.models.Comment

@Entity
data class CommentsEntity(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int,
    @PrimaryKey val commentId: Int? = null
) {
    fun toComment(): Comment {
        return Comment(
            body = body,
            email = email,
            id = id,
            name = name,
            postId = postId
        )
    }
}
