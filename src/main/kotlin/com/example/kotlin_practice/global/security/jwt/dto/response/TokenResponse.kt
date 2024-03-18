package com.example.kotlin_practice.global.security.jwt.dto.response

import java.util.*

data class TokenResponse(
    val accessToken: String,
    val accessExpiredAt: Date
)