package com.example.kotlin_practice.domain.user.dto.request

data class LoginRequest(
    val accountId: String,
    val password: String
)
