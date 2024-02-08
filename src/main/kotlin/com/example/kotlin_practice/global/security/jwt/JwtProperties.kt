package com.example.kotlin_practice.global.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val header: String,
    val prefix: String,
    val secret: String,
    val accessExpiration: Long
) {
}