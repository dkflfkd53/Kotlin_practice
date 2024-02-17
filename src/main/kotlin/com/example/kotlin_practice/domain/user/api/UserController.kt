package com.example.kotlin_practice.domain.user.api

import com.example.kotlin_practice.domain.user.application.SignupService
import com.example.kotlin_practice.domain.user.dto.request.SignupRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(
    private val signupService: SignupService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody request: SignupRequest) {
        signupService.signup(request)
    }
}