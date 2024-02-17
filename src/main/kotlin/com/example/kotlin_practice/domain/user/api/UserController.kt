package com.example.kotlin_practice.domain.user.api

import com.example.kotlin_practice.domain.user.application.LoginService
import com.example.kotlin_practice.domain.user.application.SignupService
import com.example.kotlin_practice.domain.user.dto.request.LoginRequest
import com.example.kotlin_practice.domain.user.dto.request.SignupRequest
import com.example.kotlin_practice.global.security.jwt.dto.response.TokenResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val signupService: SignupService,
    private val loginService: LoginService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest) {
        signupService.signup(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): TokenResponse {
        return loginService.login(request)
    }
}