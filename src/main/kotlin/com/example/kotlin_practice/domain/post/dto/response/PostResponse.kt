package com.example.kotlin_practice.domain.post.dto.response

data class PostResponse(
    val postId: Long,
    val title: String,
    val content: String
)