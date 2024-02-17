package com.example.kotlin_practice.domain.user.dto.request

data class SignupRequest(
    val accountId: String,
    val password: String,
    val userName: String
)
