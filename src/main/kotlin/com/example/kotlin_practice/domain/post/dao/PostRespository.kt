package com.example.kotlin_practice.domain.post.dao

import com.example.kotlin_practice.domain.post.domain.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long> {
    fun findByPostId(postId: Long): Post
}