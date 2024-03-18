package com.example.kotlin_practice.domain.post.application

import com.example.kotlin_practice.domain.post.dao.PostRepository
import org.springframework.stereotype.Service

@Service
class DeletePostService(
    private val postRepository: PostRepository
) {
    fun deletePost(postId: Long) {
        postRepository.deleteById(postId)
    }
}