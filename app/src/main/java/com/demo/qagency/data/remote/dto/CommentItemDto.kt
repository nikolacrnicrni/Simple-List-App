package com.demo.qagency.data.remote.dto


import com.demo.qagency.domain.models.Comment
import com.google.gson.annotations.SerializedName

data class CommentItemDto(
    @SerializedName("body")
    val body: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("postId")
    val postId: Int
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