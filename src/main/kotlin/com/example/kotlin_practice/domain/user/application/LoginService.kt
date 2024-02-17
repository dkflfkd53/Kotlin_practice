package com.example.kotlin_practice.domain.user.application

import com.example.kotlin_practice.domain.user.dao.UserRepository
import com.example.kotlin_practice.domain.user.dto.request.LoginRequest
import com.example.kotlin_practice.domain.user.exception.PasswordMissMatchException
import com.example.kotlin_practice.global.security.jwt.JwtTokenProvider
import com.example.kotlin_practice.global.security.jwt.dto.response.TokenResponse
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun login(request: LoginRequest): TokenResponse {
        if(userRepository.findByAccountId(request.accountId).password != request.password) throw PasswordMissMatchException.EXCEPTION

        return jwtTokenProvider.receiveToken(request.accountId)
    }
}