package com.example.kotlin_practice.domain.post.api

import com.example.kotlin_practice.domain.post.application.CreatePostService
import com.example.kotlin_practice.domain.post.application.GetPostListService
import com.example.kotlin_practice.domain.post.application.ReadPostDetailsService
import com.example.kotlin_practice.domain.post.application.UpdatePostService
import com.example.kotlin_practice.domain.post.dto.request.PostRequest
import com.example.kotlin_practice.domain.post.dto.response.PostListResponse
import com.example.kotlin_practice.domain.post.dto.response.PostResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
class PostController(
    private val createPostService: CreatePostService,
    private val readPostDetailsService: ReadPostDetailsService,
    private val getPostListService: GetPostListService,
    private val updatePostService: UpdatePostService
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

    @GetMapping("/all")
    fun getPostList(): List<PostListResponse> {
        return getPostListService.getPostList()
    }

    @PatchMapping("/{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody request: PostRequest) {
        updatePostService.updatePost(postId, request)
    }
}