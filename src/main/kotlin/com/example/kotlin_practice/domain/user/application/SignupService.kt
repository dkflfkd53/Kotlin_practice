package com.example.kotlin_practice.domain.user.application

import com.example.kotlin_practice.domain.user.dao.UserRepository
import com.example.kotlin_practice.domain.user.domain.User
import com.example.kotlin_practice.domain.user.dto.request.SignupRequest
import org.springframework.stereotype.Service

@Service
class SignupService(
    private val userRepository: UserRepository
) {
    fun signup(request: SignupRequest) {
        userRepository.save(
            User(
                accountId = request.accountId,
                password = request.password,
                userName = request.userName
            )
        )
    }
}