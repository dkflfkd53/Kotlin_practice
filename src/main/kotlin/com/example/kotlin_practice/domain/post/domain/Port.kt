package com.example.kotlin_practice.domain.post.domain

import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post (
    var title: String,
    var content: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long = 0

    fun updatePost(title: String, content: String) {
        this.title = title
        this.content = content
    }
}