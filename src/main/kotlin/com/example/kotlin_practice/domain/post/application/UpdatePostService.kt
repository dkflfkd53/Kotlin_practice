package com.example.kotlin_practice.domain.post.application

import com.example.kotlin_practice.domain.post.dao.PostRepository
import com.example.kotlin_practice.domain.post.domain.Post
import com.example.kotlin_practice.domain.post.dto.request.PostRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdatePostService(
    private val postRepository: PostRepository
) {
    @Transactional
    fun updatePost(id: Long, request: PostRequest) {
        val post: Post = postRepository.findByPostId(id)

        post.updatePost(request.title, request.content)
    }
}