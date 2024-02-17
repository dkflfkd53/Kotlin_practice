package com.example.kotlin_practice.domain.post.api

import com.example.kotlin_practice.domain.post.application.CreatePostService
import com.example.kotlin_practice.domain.post.application.ReadPostDetailsService
import com.example.kotlin_practice.domain.post.dto.request.PostRequest
import com.example.kotlin_practice.domain.post.dto.response.PostResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
class PostController(
    private val createPostService: CreatePostService,
    private val readPostDetailsService: ReadPostDetailsService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createPost(@RequestBody request: PostRequest) {
        createPostService.createPost(request)
    }

    @GetMapping("/{postId}")
    fun readPostDetails(@PathVariable postId: Long): PostResponse {
        return readPostDetailsService.readPostDetails(postId)
    }
}