package com.example.kotlin_practice.domain.post.application

import com.example.kotlin_practice.domain.post.dao.PostRepository
import com.example.kotlin_practice.domain.post.domain.Post
import com.example.kotlin_practice.domain.post.dto.request.PostRequest
import org.springframework.stereotype.Service

@Service
class CreatePostService(
    private val postRepository: PostRepository
) {
    fun createPost(request: PostRequest) {
        postRepository.save(
            Post(
                title = request.title,
                content = request.content
            )
        )
    }
}