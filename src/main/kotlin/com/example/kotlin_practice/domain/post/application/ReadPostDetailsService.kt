package com.example.kotlin_practice.domain.post.application

import com.example.kotlin_practice.domain.post.dao.PostRepository
import com.example.kotlin_practice.domain.post.domain.Post
import com.example.kotlin_practice.domain.post.dto.response.PostResponse
import org.springframework.stereotype.Service

@Service
class ReadPostDetailsService(
    private val postRepository: PostRepository
) {
    fun readPostDetails(postId: Long): PostResponse {
        val post: Post = postRepository.findByPostId(postId)

        return PostResponse(
            postId = postId,
            title = post.title,
            content = post.content
        )
    }
}