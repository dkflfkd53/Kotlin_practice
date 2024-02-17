package com.example.kotlin_practice.domain.post.application

import com.example.kotlin_practice.domain.post.dao.PostRepository
import com.example.kotlin_practice.domain.post.dto.response.PostListResponse
import org.springframework.stereotype.Service

@Service
class GetPostListService(
    private val postRepository: PostRepository
) {
    fun getPostList(): List<PostListResponse> {
        return postRepository.findAll()
            .map {
                PostListResponse(
                    postId = it.postId,
                    title = it.title
                )
            }
            .toList()
    }
}