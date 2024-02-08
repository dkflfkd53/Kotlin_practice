package com.example.kotlin_practice.domain.post.api

import com.example.kotlin_practice.domain.post.application.CreatePostService
import com.example.kotlin_practice.domain.post.dto.request.PostRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/post")
class PostController(
    private val createPostService: CreatePostService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createPost(@RequestBody request: PostRequest) {
        createPostService.createPost(request)
    }
}