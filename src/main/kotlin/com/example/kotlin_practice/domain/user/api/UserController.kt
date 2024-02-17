package com.example.kotlin_practice.domain.user.api

import com.example.kotlin_practice.domain.user.application.SignupService
import com.example.kotlin_practice.domain.user.dto.request.SignupRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val signupService: SignupService
) {
    fun signup(@RequestBody request: SignupRequest) {
        signupService.signup(request)
    }
}